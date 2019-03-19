Feature: TwsApplication_First

  Scenario Outline: ListingPropDetails
    Given I launch TWS login panel
    When I am login in to Tws Application
    Then I am clicking on listing "Listings"
    Then I am clicking on AddListings"AddListings"
    Then I am entering ListingsPropDetails"<Style>","<Parking>","<NumberofUnits>"
    And I am entering ListingsPropDetails1"<Beds>","<Baths>","<PartialBaths>"
    And I am entering ListingsPropDetails2"<SquareFootage>","<LotSize>","<Age>"

    Examples: 
      | link | Style  | NumberofUnits | Beds | Baths | PartialBaths | SquareFootage | LotSize | Age | Parking             |
      | Home | Duplex |           1.5 |    4 |     2 |            2 |          1400 |    3000 |  35 | 1 / Attached Garage |
