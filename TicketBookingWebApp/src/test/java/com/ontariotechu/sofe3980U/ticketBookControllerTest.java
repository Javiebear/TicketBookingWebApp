//package com.ontariotechu.sofe3980U;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(ticketBookControllerTest.class)
//public class ticketBookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testGetAllAirportsWithDirectFlights() throws Exception {
//        this.mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("airports"))
//                .andExpect(view().name("index"));
//    }
//
//    @Test
//    public void testSetDestinationToJFK() throws Exception {
//        this.mockMvc.perform(get("/jfkdes"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("destination"))
//                .andExpect(model().attribute("destination", "JFK"))
//                .andExpect(view().name("jfkorigin"));
//    }
//
//    @Test
//    public void testSetDestinationToLAX() throws Exception {
//        this.mockMvc.perform(get("/laxdes"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("destination"))
//                .andExpect(model().attribute("destination", "LAX"))
//                .andExpect(view().name("laxorigin"));
//    }
//
//    @Test
//    public void testSetDestinationToDEN() throws Exception {
//        this.mockMvc.perform(get("/dendes"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("destination"))
//                .andExpect(model().attribute("destination", "DEN"))
//                .andExpect(view().name("denorigin"));
//    }
//
//    @Test
//    public void testSetDestinationToYYZ() throws Exception {
//        this.mockMvc.perform(get("/yyzdes"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("destination"))
//                .andExpect(model().attribute("destination", "YYZ"))
//                .andExpect(view().name("yyzorigin"));
//    }
//}
