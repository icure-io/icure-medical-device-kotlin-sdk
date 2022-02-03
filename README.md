# io.icure.md.client - Kotlin client library for ICure Medical Device Micro Service

## Requires

* Kotlin 1.4.30
* Gradle 6.8.3

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://127.0.0.1:8912*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*CodingApi* | [**createOrModifyCoding**](docs/CodingApi.md#createormodifycoding) | **PUT** /rest/v2/coding | Create a Coding
*CodingApi* | [**createOrModifyCodings**](docs/CodingApi.md#createormodifycodings) | **PUT** /rest/v2/coding/batch | Create a Coding
*CodingApi* | [**deleteCoding**](docs/CodingApi.md#deletecoding) | **DELETE** /rest/v2/coding/{id} | Delete a Coding
*CodingApi* | [**filterCoding**](docs/CodingApi.md#filtercoding) | **POST** /rest/v2/coding/filter | Find Codings using a filter
*CodingApi* | [**getCoding**](docs/CodingApi.md#getcoding) | **GET** /rest/v2/coding/{id} | Get a Coding
*CodingApi* | [**matchCoding**](docs/CodingApi.md#matchcoding) | **POST** /rest/v2/coding/match | Find Codings using a filter
*DataSampleApi* | [**createOrModifyDataSample**](docs/DataSampleApi.md#createormodifydatasample) | **PUT** /rest/v2/data/sample | Create a DataSample
*DataSampleApi* | [**createOrModifyDataSamples**](docs/DataSampleApi.md#createormodifydatasamples) | **PUT** /rest/v2/data/sample/batch | Create a batch of Data samples
*DataSampleApi* | [**deleteAttachment**](docs/DataSampleApi.md#deleteattachment) | **DELETE** /rest/v2/data/sample/{id}/attachment/{documentId} | Delete a Data sample attachment
*DataSampleApi* | [**deleteDataSample**](docs/DataSampleApi.md#deletedatasample) | **DELETE** /rest/v2/data/sample/{id} | Delete a DataSample
*DataSampleApi* | [**deleteDataSamples**](docs/DataSampleApi.md#deletedatasamples) | **DELETE** /rest/v2/data/sample/batch/{ids} | Delete a batch of Data samples
*DataSampleApi* | [**filterDataSample**](docs/DataSampleApi.md#filterdatasample) | **POST** /rest/v2/data/sample/filter | Find Data samples using a filter
*DataSampleApi* | [**getDataSample**](docs/DataSampleApi.md#getdatasample) | **GET** /rest/v2/data/sample/{id} | Get a DataSample
*DataSampleApi* | [**getDataSampleAttachment**](docs/DataSampleApi.md#getdatasampleattachment) | **GET** /rest/v2/data/sample/{id}/attachment/{documentId} | Get a DataSample attachment metadata
*DataSampleApi* | [**getDataSampleAttachmentContent**](docs/DataSampleApi.md#getdatasampleattachmentcontent) | **GET** /rest/v2/data/sample/{id}/attachment/{documentId}/{attachmentId} | Get a Data sample attachment metadata
*DataSampleApi* | [**matchDataSample**](docs/DataSampleApi.md#matchdatasample) | **POST** /rest/v2/data/sample/match | Find Data samples using a filter
*DataSampleApi* | [**setDataSampleAttachment**](docs/DataSampleApi.md#setdatasampleattachment) | **PUT** /rest/v2/data/sample/{id}/attachment/{documentId} | Create a DataSample
*DeviceApi* | [**createOrModifyMedicalDevice**](docs/DeviceApi.md#createormodifymedicaldevice) | **PUT** /rest/v2/medical/device | Create or update a Device
*DeviceApi* | [**createOrModifyMedicalDevices**](docs/DeviceApi.md#createormodifymedicaldevices) | **PUT** /rest/v2/medical/device/batch | Create or update a batch of Devices
*DeviceApi* | [**deleteMedicalDevice**](docs/DeviceApi.md#deletemedicaldevice) | **DELETE** /rest/v2/medical/device/{medicalDeviceId} | Delete a Device
*DeviceApi* | [**deleteMedicalDevices**](docs/DeviceApi.md#deletemedicaldevices) | **POST** /rest/v2/medical/device/batch | Delete Devices
*DeviceApi* | [**filterMedicalDevices**](docs/DeviceApi.md#filtermedicaldevices) | **POST** /rest/v2/medical/device/filter | Find Devices using a filter
*DeviceApi* | [**getMedicalDevice**](docs/DeviceApi.md#getmedicaldevice) | **GET** /rest/v2/medical/device/{medicalDeviceId} | Get a Medical Device
*DeviceApi* | [**matchMedicalDevices**](docs/DeviceApi.md#matchmedicaldevices) | **POST** /rest/v2/medical/device/match | Find Devices using a filter
*HealthcareElementApi* | [**createOrModifyHealthcareElement**](docs/HealthcareElementApi.md#createormodifyhealthcareelement) | **PUT** /rest/v2/hce | Create a Healthcare Element
*HealthcareElementApi* | [**createOrModifyHealthcareElements**](docs/HealthcareElementApi.md#createormodifyhealthcareelements) | **PUT** /rest/v2/hce/batch | Create a Healthcare Element
*HealthcareElementApi* | [**deleteHealthcareElement**](docs/HealthcareElementApi.md#deletehealthcareelement) | **DELETE** /rest/v2/hce/{id} | Delete a Healthcare Element
*HealthcareElementApi* | [**filterHealthcareElement**](docs/HealthcareElementApi.md#filterhealthcareelement) | **POST** /rest/v2/hce/filter | Find Healthcare Elements using a filter
*HealthcareElementApi* | [**getHealthcareElement**](docs/HealthcareElementApi.md#gethealthcareelement) | **GET** /rest/v2/hce/{id} | Get a Healthcare Element
*HealthcareElementApi* | [**matchHealthcareElement**](docs/HealthcareElementApi.md#matchhealthcareelement) | **POST** /rest/v2/hce/match | Find Healthcare Elements using a filter
*HealthcareProfessionalApi* | [**createOrModifyHealthcareProfessional**](docs/HealthcareProfessionalApi.md#createormodifyhealthcareprofessional) | **PUT** /rest/v2/healthcareprofessional | Create a HealthcareProfessional
*HealthcareProfessionalApi* | [**deleteHealthcareProfessional**](docs/HealthcareProfessionalApi.md#deletehealthcareprofessional) | **DELETE** /rest/v2/healthcareprofessional/{id} | Delete a HealthcareProfessional
*HealthcareProfessionalApi* | [**filterHealthcareProfessionalBy**](docs/HealthcareProfessionalApi.md#filterhealthcareprofessionalby) | **POST** /rest/v2/healthcareprofessional/filter | Find Healthcare Professional using a filter
*HealthcareProfessionalApi* | [**getHealthcareProfessional**](docs/HealthcareProfessionalApi.md#gethealthcareprofessional) | **GET** /rest/v2/healthcareprofessional/{id} | Get a HealthcareProfessional
*HealthcareProfessionalApi* | [**matchHealthcareProfessionalBy**](docs/HealthcareProfessionalApi.md#matchhealthcareprofessionalby) | **POST** /rest/v2/healthcareprofessional/match | Find Data samples using a filter
*PatientApi* | [**createOrModifyPatient**](docs/PatientApi.md#createormodifypatient) | **PUT** /rest/v2/patient | Create or update a Patient
*PatientApi* | [**deletePatient**](docs/PatientApi.md#deletepatient) | **DELETE** /rest/v2/patient/{id} | Delete a Patient
*PatientApi* | [**filterPatients**](docs/PatientApi.md#filterpatients) | **POST** /rest/v2/patient/filter | Find Patients using a filter
*PatientApi* | [**getPatient**](docs/PatientApi.md#getpatient) | **GET** /rest/v2/patient/{id} | Get a Patient
*PatientApi* | [**matchPatients**](docs/PatientApi.md#matchpatients) | **POST** /rest/v2/patient/match | Find Patients using a filter
*UserApi* | [**checkTokenValidity**](docs/UserApi.md#checktokenvalidity) | **GET** /rest/v2/user/token/{userId} | Check token validity for a user.
*UserApi* | [**createOrModifyUser**](docs/UserApi.md#createormodifyuser) | **PUT** /rest/v2/user | Create a new user or modify an existing one.
*UserApi* | [**createToken**](docs/UserApi.md#createtoken) | **POST** /rest/v2/user/token/{userId} | Create a token for a user.
*UserApi* | [**deleteUser**](docs/UserApi.md#deleteuser) | **DELETE** /rest/v2/user/{userId} | Delete an existing user.
*UserApi* | [**filterUsers**](docs/UserApi.md#filterusers) | **POST** /rest/v2/user/filter | Load users from the database by filtering them using the provided Filter.
*UserApi* | [**getLoggedUser**](docs/UserApi.md#getloggeduser) | **GET** /rest/v2/user | Get the details of the logged User.
*UserApi* | [**getUser**](docs/UserApi.md#getuser) | **GET** /rest/v2/user/{userId} | Get a User by id.
*UserApi* | [**matchUsers**](docs/UserApi.md#matchusers) | **POST** /rest/v2/user/match | Load user ids from the database by filtering them using the provided Filter.


<a name="documentation-for-models"></a>
## Documentation for Models

 - [io.icure.md.client.models.Address](docs/Address.md)
 - [io.icure.md.client.models.AuthenticationToken](docs/AuthenticationToken.md)
 - [io.icure.md.client.models.Coding](docs/Coding.md)
 - [io.icure.md.client.models.CodingReference](docs/CodingReference.md)
 - [io.icure.md.client.models.Content](docs/Content.md)
 - [io.icure.md.client.models.DataSample](docs/DataSample.md)
 - [io.icure.md.client.models.Document](docs/Document.md)
 - [io.icure.md.client.models.Filter](docs/Filter.md)
 - [io.icure.md.client.models.HealthcareElement](docs/HealthcareElement.md)
 - [io.icure.md.client.models.HealthcareProfessional](docs/HealthcareProfessional.md)
 - [io.icure.md.client.models.Identifier](docs/Identifier.md)
 - [io.icure.md.client.models.Measure](docs/Measure.md)
 - [io.icure.md.client.models.MedicalDevice](docs/MedicalDevice.md)
 - [io.icure.md.client.models.PaginatedDocumentKeyAndIdPairObject](docs/PaginatedDocumentKeyAndIdPairObject.md)
 - [io.icure.md.client.models.PaginatedListCoding](docs/PaginatedListCoding.md)
 - [io.icure.md.client.models.PaginatedListDataSample](docs/PaginatedListDataSample.md)
 - [io.icure.md.client.models.PaginatedListHealthcareElement](docs/PaginatedListHealthcareElement.md)
 - [io.icure.md.client.models.PaginatedListHealthcareProfessional](docs/PaginatedListHealthcareProfessional.md)
 - [io.icure.md.client.models.PaginatedListMedicalDevice](docs/PaginatedListMedicalDevice.md)
 - [io.icure.md.client.models.PaginatedListPatient](docs/PaginatedListPatient.md)
 - [io.icure.md.client.models.PaginatedListUser](docs/PaginatedListUser.md)
 - [io.icure.md.client.models.Partnership](docs/Partnership.md)
 - [io.icure.md.client.models.Patient](docs/Patient.md)
 - [io.icure.md.client.models.PatientHealthCareParty](docs/PatientHealthCareParty.md)
 - [io.icure.md.client.models.PersonName](docs/PersonName.md)
 - [io.icure.md.client.models.Property](docs/Property.md)
 - [io.icure.md.client.models.PropertyType](docs/PropertyType.md)
 - [io.icure.md.client.models.Telecom](docs/Telecom.md)
 - [io.icure.md.client.models.TimeSeries](docs/TimeSeries.md)
 - [io.icure.md.client.models.TypedValueObject](docs/TypedValueObject.md)
 - [io.icure.md.client.models.User](docs/User.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
