package ncl.devops.api.cloudvendor.service.impl;

import ncl.devops.api.cloudvendor.model.CloudVendor;
import ncl.devops.api.cloudvendor.service.CloudVendorService;
import ncl.devops.api.cloudvendor.repository.CloudVendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {

//    @Mock
//    private CloudVendorRepository cloudVendorRepository;
//    private CloudVendorService cloudVendorService;

    @Mock
    CloudVendorRepository cloudVendorRepository;

    @Mock
    CloudVendorService mockCloudVendorService;

    @InjectMocks
    CloudVendorServiceImpl cloudVendorService;

    CloudVendor cloudVendor;
    AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
//        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
//        cloudVendor = new CloudVendor("V1", "Vendor1 Name", "Vendor1 Address", "xxxxx");
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendorRepository.class);
        mock(CloudVendor.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Cloud Vendor Save success");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendorRepository.class);
        mock(CloudVendor.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Cloud Vendor Data Update Success");
    }

    @Test
    void testDeleteCloudVendor() {
        String cloudVendorId = "V1";
        doNothing().when(cloudVendorRepository).deleteById(cloudVendorId);
        String result = cloudVendorService.deleteCloudVendor(cloudVendorId);
        assertThat(result).isEqualTo("Cloud Vendor Delete Success");
    }

//    @Test
//    void testGetCloudVendor() {
//        mock(CloudVendorRepository.class);
//        mock(CloudVendor.class);
//
//        when(cloudVendorRepository.findById("V1")).thenReturn(Optional.ofNullable(cloudVendor));
//        assertThat(cloudVendorService.getCloudVendor("V1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cloudvendor/controller/getCloudVendorsTest.txt", numLinesToSkip = 1)
    void getCloudVendorsTest(String vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber) {
        cloudVendor = new CloudVendor(vendorId, vendorName, vendorAddress, vendorPhoneNumber);
        when(cloudVendorRepository.findById(vendorId)).thenReturn(Optional.of(cloudVendor));

        CloudVendor result = cloudVendorService.getCloudVendor(vendorId);

        assertEquals(cloudVendor, result);
    }

//    @Test
//    void testGetAllCloudVendors() {
//        mock(CloudVendorRepository.class);
//        mock(CloudVendor.class);
//
//        when(cloudVendorRepository.findAll()).thenReturn(List.of(cloudVendor));
//        assertThat(cloudVendorService.getAllCloudVendors()).isEqualTo(List.of(cloudVendor));
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cloudvendor/controller/getCloudVendorsTest.txt", numLinesToSkip = 1)
    void getAllCloudVendorsTest(String vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber) {
        List<CloudVendor> cloudVendors = List.of(new CloudVendor(vendorId, vendorName, vendorAddress, vendorPhoneNumber));
        when(cloudVendorRepository.findAll()).thenReturn(cloudVendors);

        List<CloudVendor> result = cloudVendorService.getAllCloudVendors();

        assertEquals(cloudVendors, result);
    }
//    @Test
//    void testHello() {
//        assertThat(cloudVendorService.hello()).isEqualTo("Hello");
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cloudvendor/controller/helloMessages.txt", numLinesToSkip = 1)
    void helloTest(String expectedMessage) {
        when(mockCloudVendorService.hello()).thenReturn(expectedMessage);

        String result = mockCloudVendorService.hello();

        assertEquals(expectedMessage, result);
    }
}