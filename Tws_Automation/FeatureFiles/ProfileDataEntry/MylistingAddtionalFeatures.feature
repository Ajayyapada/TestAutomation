Feature: TwsApplication_First

  Scenario Outline: ListingAdditionalDetails
    Given I launch TWS login panel
    When I am login in to Tws Application
    Then I am clicking on listing "Listings"
    Then I am clicking on AddListings"AddListings"
    Then I am entering AdditionalDetails1"<CondoFees>","<FeeSchedule>","<TaxYear>"
    And I am entering AdditionalDetails2"<MonthlyRent>","<Taxes>","<InteriorFeatures>"
    And I am entering AdditionalDetails3"<ExteriorFeatures>","<AdditionalFeaturesandAmenities>","<Remarks>"


    Examples: 
      | link | CondoFees  | FeeSchedule | TaxYear | MonthlyRent | Taxes | InteriorFeatures | ExteriorFeatures | AdditionalFeaturesandAmenities | Remarks |
      | Home | 7878787.00 | Quarterly   |    2018 |        1200 |  1400 | InteriorFeatures | ExteriorFeatures | AdditionalFeaturesandAmenities | Remarks |
