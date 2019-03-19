Feature: TwsApplication_First

  Scenario Outline: NewListingDetails
    Given I launch TWS login panel
    When I am login in to Tws Application
    Then I am clicking on listing "Listings"
    Then I am clicking on AddListings"AddListings"
    And I am entering"<Status>","<MLSID>","<ListingPrice>"
    And I am entering infopart2"<ListingDate>","<VirtualTourURL>","<ShortDescription>"
    Then I am entering PropAdress1"<HouseNo>","<SuiteNo>","<AddressLine1>","<AddressLine2>"
    And I am entering PropAdress2"<City>","<Province>","<Country>","<PostalCode>"
    And I am entering PropAdress3"<Area>","<SchoolDistrict>","<PropertyType>"

    Examples: 
      | link | Status | MLSID  | ListingPrice | ListingDate | VirtualTourURL      | ShortDescription | HouseNo | SuiteNo | AddressLine1                  | AddressLine2 | PostalCode | Area      | SchoolDistrict | PropertyType | City     | Province | Country       |
      | Home | Sold   | LA1234 |        40000 | 27/08/2019  | www.virtualtour.com | Home Sweet Home  |     575 |     204 | S Virginia Hills Dr Unit 2401 | McKinney     |      75072 | New Homes | SchoolDistrict | Duplex       | McKinney | TX       | United States |
