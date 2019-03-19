Feature: TwsApplication_First

  Scenario Outline: AgentProfileDetails
    Given I launch TWS login panel
    When I am login in to Tws Application
    Then I am clicking on "Listings"
    Then I am clicking on myProfile"AddListings"
    Then I am entering"<OpenHouseDate>","<StartTime>","<StartTime>","<EndTime>","<DrivingInstructions>","<CompellingFeatures>","<Neighborhood>","<OpenHouseNotes>"
    Then I am verifying customsite

    Examples: 
      | link | OpenHouseDate | StartTime | EndTime  | DrivingInstructions | CompellingFeatures | Neighborhood | OpenHouseNotes |
      | Home | 28/08/2019    | 10:00 AM  | 12:00 PM | DrivingInstructions | CompellingFeatures | Neighborhood | OpenHouseNotes | 
