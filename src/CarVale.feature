Feature: CarWale 
Scenario: To Find Car with less KM usage 
Given User opens the Chrome browser
And maximises the browser 
And launches the CarWale application 
And clicks Used option 
And selects city as Chennai 
And selects budget min 8L and max 12L  
And clicks Search  
And selects Cars with Photos under Only Show Cars with from the search results 
And selects Manufacturer as Hyundai and car as Creta 
And selects Fuel type as Petrol 
And sorts the results as KM: Low to High 
And validates the Cars listed with KM Low to High and adds the least KM run car to Wishlist
And goes to Wishlist and Click on More Details 
When prints all the details under Overview in the Same way as displayed in application 
Then Close the browser. 