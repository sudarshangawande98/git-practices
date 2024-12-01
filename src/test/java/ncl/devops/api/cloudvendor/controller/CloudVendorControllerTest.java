package ncl.devops.api.cloudvendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ncl.devops.api.cloudvendor.model.CloudVendor;
import ncl.devops.api.cloudvendor.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CloudVendorService cloudVendorService;

    CloudVendor cloudVendor1;
    CloudVendor cloudVendor2;

    List<CloudVendor> cloudVendorList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cloudVendor1 = new CloudVendor("1", "AWS", "Amazon Web Services", "Amazon Web Services");
        cloudVendor2 = new CloudVendor("2", "Azure", "Microsoft Azure", "Microsoft Azure");

        //ArrayList<>(Array.asList(cloudVendor1, cloudVendor2));
        cloudVendorList.add(cloudVendor1);
        cloudVendorList.add(cloudVendor2);
    }

    @AfterEach
    void tearDown() {
        cloudVendor1 = null;
        cloudVendor2 = null;
        cloudVendorList = null;
    }

    @Test
    void getCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendor1);

        this.mockMvc.perform(get("/cloudVendor/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);

        this.mockMvc.perform(get("/cloudVendor"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createCloudVendorDetails() throws Exception {
        //Convert Java Object to JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
//        String requestJson = objectWriter.writeValueAsString(cloudVendor1);

        // Convert Java Object to JSON
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendor1);

        when(cloudVendorService.createCloudVendor(cloudVendor1)).thenReturn("Cloud Vendor Created Successfully");

        this.mockMvc.perform(post("/cloudVendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateCloudVendorDetails() throws Exception {
        //Convert Java Object to JSON
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(cloudVendor1);

        when(cloudVendorService.updateCloudVendor(cloudVendor1)).thenReturn("Cloud Vendor Data Update Success");

        this.mockMvc.perform(put("/cloudVendor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteCloudVendorDetails() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1")).thenReturn("Cloud Vendor Deleted Successfully");

        this.mockMvc.perform(delete("/cloudVendor/1"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void hello() throws Exception {
        this.mockMvc.perform(get("/cloudVendor"))
                .andDo(print()).andExpect(status().isOk());
    }
}