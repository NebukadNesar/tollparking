package com.example.tollparking;

import com.example.tollparking.api.constants.ParkingConstants;
import com.example.tollparking.api.park.ParkingService;
import com.example.tollparking.api.requestresponse.Request;
import com.example.tollparking.api.requestresponse.Response;
import com.example.tollparking.api.slot.Slot;
import com.example.tollparking.api.statistic.StatisticsResponse;
import com.example.tollparking.api.validation.ParkingException;
import com.example.tollparking.api.vehicle.Vehicle;
import com.google.gson.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.tollparking.api.requestresponse.ResponseCodes.NONE_EMPTY_SLOT;
import static com.example.tollparking.api.requestresponse.ResponseCodes.PARK_SUCCESS_CODE;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TollparkingApplicationTests {

	JsonArray   policiesArray     = null;
	JsonObject  slotPoliciesArray = null;
	Set<String> policyNamesSet    = new HashSet<>();


	@BeforeAll
	void testExistConfigFile() {
		File    tmpDir = new File("config-properties.json");
		boolean exists = tmpDir.exists();
		Assertions.assertTrue(exists, "Config Property file does not exist!");
	}

	@Test
	@Order(1)
	void testIfPolicyConfigContainsData() {
		try {
			Reader      reader      = new FileReader("config-properties.json");
			JsonElement jsonElement = JsonParser.parseReader(reader);

			JsonObject config = jsonElement.getAsJsonObject();
			Assertions.assertNotNull(config, "Config property file not properly constructed json file.");

			policiesArray     = config.getAsJsonArray("policy-properties");
			slotPoliciesArray = config.getAsJsonObject("slot-policies");

		} catch (FileNotFoundException e) {
			Assertions.fail("Config Property file is not found.");
		} catch (JsonSyntaxException e) {
			Assertions.fail("Config Property json syntax is not correct.");
		} catch (IllegalStateException e) {
			Assertions.fail("Property data is of another type not a json.");
		}
	}


	@Test
	@Order(2)
	public void testIfPoliciesArrayContainstData() {
		Assertions.assertNotNull(policiesArray, "policies array is null.");
		for (JsonElement js : policiesArray) {
			JsonObject jsonObject = js.getAsJsonObject();
			Assertions.assertNotNull(jsonObject.get("name"), "policy name cannot be null");
			Assertions.assertNotNull(jsonObject.get("default-price"), "policy default price cannot be null");
			Assertions.assertNotNull(jsonObject.get("hourly-price"), "policy hourly price cannot be null");

			/**
			 * filling policies set with policy names
			 */
			policyNamesSet.add(jsonObject.get("name").getAsString());
		}
	}

	@Test
	@Order(3)
	public void testIfSlotPoliciesExists() {
		/**
		 * To see if slot policies are really the policies that are defined in policy set
		 */
		JsonArray sedan = slotPoliciesArray.getAsJsonArray("sedan-policies");
		JsonArray ec20w = slotPoliciesArray.getAsJsonArray("ec20w-policies");
		JsonArray ec50w = slotPoliciesArray.getAsJsonArray("ec50w-policies");

		Assertions.assertNotNull(sedan, "sedan slot policies array is null");
		Assertions.assertNotNull(ec20w, "ec20w slot policies array is null");
		Assertions.assertNotNull(ec50w, "ec50w slot policies array is null");
	}


	@Test
	@Order(4)
	public void testIfSedanSlotPoliciesArrayContainstData() {
		Assertions.assertNotNull(slotPoliciesArray, "slot policies array is null.");
		JsonArray sedan = slotPoliciesArray.getAsJsonArray("sedan-policies");
		for (JsonElement js : sedan) {
			String policyName = js.getAsString();
			Assertions.assertTrue(policyNamesSet.contains(policyName), "policy " + policyName + " does not exist in policies set.");
		}
	}


	@Test
	@Order(5)
	public void testIfEC20WattSlotPoliciesArrayContainstData() {
		Assertions.assertNotNull(slotPoliciesArray, "slot policies array is null.");
		JsonArray ec20w = slotPoliciesArray.getAsJsonArray("ec20w-policies");
		for (JsonElement js : ec20w) {
			String policyName = js.getAsString();
			Assertions.assertTrue(policyNamesSet.contains(policyName), "policy " + policyName + " does not exist in policies set.");
		}
	}


	@Test
	@Order(6)
	public void testIfEC50WattSlotPoliciesArrayContainstData() {
		Assertions.assertNotNull(slotPoliciesArray, "slot policies array is null.");
		JsonArray ec50w = slotPoliciesArray.getAsJsonArray("ec50w-policies");
		for (JsonElement js : ec50w) {
			String policyName = js.getAsString();
			Assertions.assertTrue(policyNamesSet.contains(policyName), "policy " + policyName + " does not exist in policies set.");
		}
	}

	@Test
	@Order(7)
	public void testParkingServiceInstance() {
		ParkingService parkingService = ParkingService.getInstance();
		Assertions.assertNotNull(parkingService, "parking service is not initilized.");
	}

	@Test
	@Order(8)
	public void testNumberofSlotWithInitializedValuesOfSedan() throws ParkingException {
		ParkingService     parkingService     = ParkingService.getInstance();
		StatisticsResponse statisticsResponse = parkingService.stats();
		List<Slot>         sedanSlot          = statisticsResponse.getEc20WattSlot();
		final int          sedanSlotCount     = ParkingConstants.SEDAN_SLOT_COUNT;
		Assertions.assertTrue(sedanSlotCount == sedanSlot.size(), "initialized sedan slot count does not match with the expected slot count.");
	}

	@Test
	@Order(9)
	public void testNumberofSlotWithInitializedValuesOfEC20() throws ParkingException {
		ParkingService     parkingService     = ParkingService.getInstance();
		StatisticsResponse statisticsResponse = parkingService.stats();
		List<Slot>         ec20Slots          = statisticsResponse.getEc20WattSlot();
		final int          ec20WSlotCount     = ParkingConstants.SEDAN_SLOT_COUNT;
		Assertions.assertTrue(ec20WSlotCount == ec20Slots.size(), "initialized ec20W slot count does not match with the expected slot count.");
	}

	@Test
	@Order(10)
	public void testNumberofSlotWithInitializedValuesOfEC50() throws ParkingException {
		ParkingService     parkingService     = ParkingService.getInstance();
		StatisticsResponse statisticsResponse = parkingService.stats();
		List<Slot>         ec50Slots          = statisticsResponse.getEc20WattSlot();
		final int          ec50WSlotCount     = ParkingConstants.SEDAN_SLOT_COUNT;
		Assertions.assertTrue(ec50WSlotCount == ec50Slots.size(), "Slot size: " + ec50Slots.size() + " initialized ec50W slot count does not match with the expected slot count.");
	}

	@Test
	@Order(11)
	public void testParkingAVehicle() throws ParkingException {
		ParkingService parkingService = ParkingService.getInstance();
		Request        parkRequest    = new Request();
		parkRequest.setVehicle(new Vehicle(ParkingConstants.SEDAN));
		Response parkingResponse = parkingService.park(parkRequest);
		Assertions.assertNotNull(parkingResponse, "parking response returned null, response should be with status code and desc");

		if (parkingResponse.getStatus() == PARK_SUCCESS_CODE)
			Assertions.assertTrue(parkingResponse.isSuccessFull(), "vehicle parking status is unsuccessful");

		Assertions.assertTrue(parkingResponse.isSuccessFull(), "parking is unsuccessful");
	}


	@Test
	@Order(12)
	public void testParkingAVehicleAtExceededSedanSlot() throws ParkingException {
		ParkingService parkingService         = ParkingService.getInstance();
		Request        parkRequest            = new Request();
		Request        exceedingParkRequest   = new Request();
		int            numberOfFreeSedanSlots = 0;

		StatisticsResponse statisticsResponse = parkingService.stats();
		List<Slot>         sedanSlots         = statisticsResponse.getSedanSlot();

		for (Slot slot : sedanSlots) {
			if (Slot.SlotStatus.EMPTY.equals(slot.getStatus())) {
				numberOfFreeSedanSlots++;
			}
		}

		for (int i = 0; i < numberOfFreeSedanSlots; i++) {
			Response response = parkingService.park(parkRequest);
			Assertions.assertTrue(response.getStatus() == PARK_SUCCESS_CODE, "there is a free slot for sedan but cannot park.");
		}

		Response exceededParkingResponse = parkingService.park(exceedingParkRequest);
		Assertions.assertTrue(exceededParkingResponse.getStatus() == NONE_EMPTY_SLOT, "exceeding parking request for sedan did not return correct response code.");
	}

	@Test
	@Order(13)
	public void testParkingAVehicleAtExceededEC20WattSlot() throws ParkingException {
		ParkingService parkingService       = ParkingService.getInstance();
		Request        parkRequest          = new Request();
		Request        exceedingParkRequest = new Request();
		int            numberOfFreeSlots    = 0;

		StatisticsResponse statisticsResponse = parkingService.stats();
		List<Slot>         ec20Slots          = statisticsResponse.getEc20WattSlot();

		for (Slot slot : ec20Slots) {
			if (Slot.SlotStatus.EMPTY.equals(slot.getStatus())) {
				numberOfFreeSlots++;
			}
		}

		for (int i = 0; i < numberOfFreeSlots; i++) {
			Response response = parkingService.park(parkRequest);
			Assertions.assertTrue(response.getStatus() == PARK_SUCCESS_CODE, "there is a free slot for ec20 but cannot park.");
		}

		Response exceededParkingResponse = parkingService.park(exceedingParkRequest);
		Assertions.assertTrue(exceededParkingResponse.getStatus() == NONE_EMPTY_SLOT, "exceeding parking request for ec20 did not return correct response code.");
	}


	@Test
	@Order(14)
	public void testParkingAVehicleAtExceededEC50WattSlot() throws ParkingException {
		ParkingService parkingService       = ParkingService.getInstance();
		Request        parkRequest          = new Request();
		Request        exceedingParkRequest = new Request();
		int            numberOfFreeSlots    = 0;

		StatisticsResponse statisticsResponse = parkingService.stats();
		List<Slot>         ec50Slots          = statisticsResponse.getEc20WattSlot();

		for (Slot slot : ec50Slots) {
			if (Slot.SlotStatus.EMPTY.equals(slot.getStatus())) {
				numberOfFreeSlots++;
			}
		}

		for (int i = 0; i < numberOfFreeSlots; i++) {
			Response response = parkingService.park(parkRequest);
			Assertions.assertTrue(response.getStatus() == PARK_SUCCESS_CODE, "there is a free slot for ec20 but cannot park.");
		}

		Response exceededParkingResponse = parkingService.park(exceedingParkRequest);
		Assertions.assertTrue(exceededParkingResponse.getStatus() == NONE_EMPTY_SLOT, "exceeding parking request for ec20 did not return correct response code.");
	}

	@Test
	@Order(15)
	public void testExceptionalResponseCaseForNullRequest() {
		ParkingException exception = Assertions.assertThrows(ParkingException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				final ParkingService parkingService = ParkingService.getInstance();

				parkingService.park(null);
			}
		});

		Assertions.assertTrue(exception.getMessage().contains("Request is null"), "for null reqest exception correct exception message is not returned.");
	}

	@Test
	@Order(16)
	public void testExceptionalResponseCaseForEmptySlotType() {
		ParkingException exception = Assertions.assertThrows(ParkingException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				final ParkingService parkingService = ParkingService.getInstance();
				Request              parkRequest    = new Request();
				parkingService.park(parkRequest);
			}
		});

		Assertions.assertTrue(exception.getMessage().contains("Vehicle is null"), "correct exception message is not returned.");
	}

	@Test
	@Order(17)
	public void testExceptionalResponseCaseForIncorrectSlotType() throws ParkingException {
		final ParkingService parkingService = ParkingService.getInstance();
		Request              parkRequest    = new Request();
		parkRequest.setVehicle(new Vehicle("Volvo"));
		Response response = parkingService.park(parkRequest);
		Assertions.assertTrue(response.getStatusDesc().contains("no such slot"), "correct error message is not returned for incorrect slot type request");
	}
}