Feature: TwsApplication_First

  Scenario Outline: TWS info
    Given I launch TWS login panel
    When I am login in to Tws Application
    Then I am clicking on "Account"
    Then I am clicking on myProfile"profilesettings"
    And I am entering"<OfficeName>","<Addressdetails>","<City>","<ZipCode>","<StateProvince>","<Country>"
    Then I am entering values"<Facebook>","<LinkedIn>","<Twitter>","<Realtor>","<Pinterest>","<Instagram>"
    Then I am verifying customsiteExpertdetails

    Examples: 
      | link | OfficeName   | Addressdetails              | City   | ZipCode | StateProvince | Country | Facebook         | LinkedIn        | Twitter         | Realtor         | Pinterest        | Instagram         |
      | Home | Ajay pvt.ltd | 18383 Preston Rd., Ste. 150 | Dallas |   75252 | AB            | Canada  | www.facebook.com | www.linkdin.com | www.twitter.com | www.realtor.com | www.pintrest.com | www.instagram.com |
