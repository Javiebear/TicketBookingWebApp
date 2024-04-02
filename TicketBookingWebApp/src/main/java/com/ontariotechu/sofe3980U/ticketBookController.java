package com.ontariotechu.sofe3980U;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@SessionAttributes({"originAirport", "destination"})
public class ticketBookController {

	// grabs the direct flights and displays them for the user
	@GetMapping("/")
	public String getAllAirportsWithDirectFlights(Model model) {
		List<String> airportsInfo = Stream.of(Application.airports)
				.map(airport -> airport.getAirportName() + ": " + String.join(", ", airport.getDirectFlight()))
				.collect(Collectors.toList());

		model.addAttribute("airports", airportsInfo);
		return "index";
	}
	// on selected webpage, that will be the selected destination used later for ticket
	@GetMapping("/jfkdes")
	public String setDestinationToJFK(Model model) {
		String destination = "JFK";
		model.addAttribute("destination", destination);
		return "jfkorigin";
	}
	@GetMapping("/laxdes")
	public String setDestinationToLAX(Model model) {
		String destination = "LAX";
		model.addAttribute("destination", destination);
		return "laxorigin";
	}
	@GetMapping("/dendes")
	public String setDestinationToDEN(Model model) {
		String destination = "DEN";
		model.addAttribute("destination", destination);
		return "cldorigin";
	}
	@GetMapping("/yyzdes")
	public String setDestinationToYYZ(Model model) {
		String destination = "YYZ";
		model.addAttribute("destination", destination);
		return "yyzorigin";
	}
	// grabs origin airport from the user
	@PostMapping("/setOrigin")
	public String setOrigin(@RequestParam("originAirport") String originAirport, HttpSession session) {
		session.setAttribute("originAirport", originAirport);
		return "ticket";
	}
	@PostMapping("/book")
	public String bookTicket(
			@RequestParam("fname") String fname,
			@RequestParam("lname") String lname,
			@RequestParam("departureDate") String departureDate,
			@RequestParam(value = "isRoundTrip", defaultValue = "false") boolean isRoundTrip,
			@RequestParam(value = "isMilitary", defaultValue = "false") boolean isMilitary,
			HttpSession session,
			Model model) throws ParseException {

		// Retrieve origin and destination from the session
		String origins = (String) session.getAttribute("originAirport");
		String destinations = (String) session.getAttribute("destination");

		int originIndex = 0;
		int destinationIndex = 0;

		if(Objects.equals(origins, "JFK")){
			originIndex = 1;
		} else if(Objects.equals(origins, "DEN")) {
			originIndex = 2;
		} else if(Objects.equals(origins, "YYZ")) {
			originIndex = 3;
		} else if(Objects.equals(origins, "LAX")) {
			originIndex = 4;
		}

		if(Objects.equals(destinations, "JFK")){
			destinationIndex = 1;
		} else if(Objects.equals(destinations, "DEN")) {
			destinationIndex = 2;
		} else if(Objects.equals(destinations, "YYZ")) {
			destinationIndex = 3;
		} else if(Objects.equals(destinations, "LAX")) {
			destinationIndex = 4;
		}
		Airport origin = Application.airports[originIndex - 1];
		Airport destination = Application.airports[destinationIndex - 1];

		Ticket newTicket = new Ticket();
		newTicket.bookTrip(origin, destination);
		boolean dirPathExists = origin.checkDirectFlight(destination);

		ArrayList<String> airportSelected = new ArrayList<>();

// Attempt to populate the newTicket object correctly
		airportSelected.add(origin.getAirportName());

		if (dirPathExists) { // If there are no direct flights
			airportSelected.add(destination.getAirportName());
		} else {
			ArrayList<String> originMulti = origin.returnMultiFlight();

			// Assume we select the first multi-flight path as an example (adjust as needed)
			if (!originMulti.isEmpty()) {
				String flightString = originMulti.get(0);
				// Splitting the string
				String[] tmpPath = flightString.split("-->");
				// Adding to the arraylist
				for (String airport : tmpPath) {
					airportSelected.add(airport);
				}
			}
		}

		newTicket.createTicket(fname, lname, departureDate, isRoundTrip, dirPathExists);
		newTicket.createAirportPath(airportSelected);
		newTicket.setFlightTime(origin.getFlightTime(newTicket.getFlightPath(), Application.airports));

		// The Departure Time
		String dTime = "";
		if(isMilitary) {
			dTime = "16:15";
		} else {
			dTime = "4:15";
		}

		String formattedFlightPath = String.join(" --> ", newTicket.getFlightPath());
		model.addAttribute("flightPath", formattedFlightPath);

		model.addAttribute("ticketInfo", newTicket);
		model.addAttribute("fname", fname);
		model.addAttribute("lname", lname);
		model.addAttribute("departureDate", departureDate);
		model.addAttribute("roundTrip", isRoundTrip ? "Yes" : "No");
		model.addAttribute("origin", origin.getAirportName());
		model.addAttribute("destination", destination.getAirportName());
		model.addAttribute("flightTime", newTicket.getFlightTime());
		model.addAttribute("dTimes", dTime);

		return "boardingpass";
	}
}
