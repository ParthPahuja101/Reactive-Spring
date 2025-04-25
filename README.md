# Setting up webflux for spring applications
## Using Spring Initializer
    Link - https://start.spring.io/
## Adding Maven Dependency

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```


## Capstone Project

```
Parking Management->
Requirements-
We need to have a list of parking slots
ParkingSlot(
Int id;
String type: [Bike,Car]
Boolean isOccupied;
)
An API to get available parking slots for car/bike.
API should return 404 is no parking slot available
ParkingRecord(
Int id;
String vehicleNumber;
Int parkingSlotId;
String CheckinTime;
String CheckoutTime;
)
An API for telling the system that Vehicle A is Checking In at Slot X.
RequestBody( VehicleNumber, SlotId)
Need to create an entry for ParkingRecord here and update the slot is occupied.
API should throw -
404 in case the slot requested is not present.
409 in case the parking slot is already occupied. 
An API for telling the system that Vehicle A is Checking Out from Slot X.
RequestBody( VehicleNumber)
Need to update the checkout time in ParkingRecord here and update the slot as not occupied.
API should throw -
404 in case the vehicle is not present.
409 in case the vehicle is already checked out.

Using Functional Web, R2DBC, Global Exception Handling and Test cases.
 




```
