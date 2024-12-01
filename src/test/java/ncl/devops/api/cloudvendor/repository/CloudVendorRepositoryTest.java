package ncl.devops.api.cloudvendor.repository;

import ncl.devops.api.cloudvendor.model.CloudVendor;
import ncl.devops.api.cloudvendor.repository.CloudVendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor = new CloudVendor("V1", "Vendor1 Name", "Vendor1 Address", "xxxxx");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    //Test Success
    @Test
    void testFindByVendorName_Found() {

        List<CloudVendor> cloudVendorsList = cloudVendorRepository.findByVendorName("Vendor1 Name");
        assertThat(cloudVendorsList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorsList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }

    //Test failure
    @Test
    void testFindByVendorName_NotFound() {

        List<CloudVendor> cloudVendorsList = cloudVendorRepository.findByVendorName("GCP");
        assertThat(cloudVendorsList.isEmpty()).isTrue();
    }
}
