 
Feature: Login&ProfiledetailsValidation

  Scenario Outline: AgentProfileDetails
    Given I launch TWS login panel
    When I am login in to Tws Application
    Then I am clicking on "Account"
    Then I am clicking on myProfile"profilesettings"
    And I am entering"<Firstname>","<Lastname>","<Email>","<Phone1>","<Phone2>","<Phone3>","<Phonetype1>","<Phonetype2>","<Phonetype3>"
    And I am entering"<PHMessage>","<SHMessage>","<ProfTitle>","<AgentLiNum>","<Exp_years>","<Specialities>","<Areas_served>","<Prof_Design>"
    Then I am verifying customsite

    Examples: 
      | link | Firstname | Lastname | Email                   | Phone1     | Phone2     | Phone3    | PHMessage             | SHMessage               | ProfTitle | AgentLiNum | Exp_years           | Specialities         | Areas_served    | Prof_Design      | Phonetype1 | Phonetype2 | Phonetype3 |
      | Home | Ajay      | Yapada   | ajay.yapada@brillio.com | 9999999999 | 8888888888 | 444444444 | Agent primary message | Agent secondary message | REALTOR®  | L1234      | 26 years experience | New Build, New Homes | Dallas , Aubrey | Property Manager | Landline   | Mobile     | Fax        |
