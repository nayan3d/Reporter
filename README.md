***************** How to run the solution is provided at the end *************************

# Implementation of a reporting web service

Implement a RESTful web service that can offer reporting advertising data, part of which
is extracted from a csv file and the rest being calculated as additional metrics.

## Scope
- Implement only the backend and leave the creation of a colourful and flashy frontend over to the frontend developers.
- Your web service should use Java SDK 8+, Spring and Maven since we use them heavily.
  You can alternatively use Gradle instead of Maven if you feel more comfortable.
- Since we put quite some emphasis on __tests__, we would also like to see how you verify the functionality 
  of your service by writing some tests.

## Guidance
- Together with the implementation, provide a short description for other developers on how to run and use your application.
  Run and build __instructions__ as well as general __documentation__ are evaluated in a positive manner, 
  as it indicates you know how to work in that environment (spoiler alert: we love documentation!).
- We also like to see your 'long division' i.e. the working out of the problem in code, so please use a public 
  git repository and add commits for significant working steps.
- Usage of standard JDK libraries is encouraged, though external libraries are not forbidden to use.
- Create a git repository for your solution and send us the git url once done. This is not mandatory though as you may send 
  the challenge as a compressed file as well.

### Definitions
Find below some definitions that will allow you to better understand the business scope of the coding challenge.
They are largely extracted from [Appnexus wiki](https://wiki.appnexus.com/display/industry/Online+Advertising+and+Ad+Tech+Glossary#OnlineAdvertisingandAdTechGlossary-publisher).

- **Advertiser**: An entity that pays money to get his advertisement shown.
- **Publisher**: The one who gets money for showing the ads on his site.
- **Creative**: The actual graphical advertisement itself.
- **Request**: In Ad Tech, it refers to a request for a creative or ad tag.
- **Demand**: Advertising demand, or the desire to buy ad space and display creatives.
- **Impression**: A creative served to a single user at a single point in time.
- **Revenue**: The amount of money a publisher earns from ads showing.
- **Conversion**: When a user makes a purchase, or performs some other desired action in response to an ad.
- **CPC**: Cost per click. A payment model in which advertisers pay each time a user clicks on their advertisement.
- **CPM**: Cost per mille or thousand (mille = thousand in Latin). A pricing model in which advertisers pay 
  for every 1000 impressions of their advertisement served. This is the standard basic pricing model for online advertising.
- **CTR (%)**: Click-through rate.  Expressed as a percentage. Literally, the ratio of users who click on a specific 
  link to the number of total users who view an advertisement.
  
  **CTR = (clicks ÷ impressions) × 100%**
  
- **CR (%)**: Conversion rate. The ratio of conversions to the number of impressions
 
  **CR = (conversions ÷ impressions) × 100%**

- **Fill Rate**: The ratio of impressions to the number of requests. It varies by inventory.

  **Fill Rate = (impressions ÷ requests) × 100%**
  
- **eCPM**: Effective Cost Per Thousand. A translation from CPM, expressed as such from a publisher's point of view.

  **eCPM = (revenue × 1000) ÷ impressions**

## Task Description
In the root folder you will find two csv files which contain publisher advertising reporting data for the months of 
January [2018_01_report.csv](2018_01_report.csv) and February [2018_02_report.csv](2018_02_report.csv).
Specifically, it lists the 4 available sites of the publisher (desktop and mobile web plus android and iOS apps) 
broken down into five dimensions (requests, impressions, clicks, conversions and revenue (USD)).

Your task is to 
1. Parse the file and store it in-memory.
   Kudos and additional points earned for using JPA together with any kind of underlying database technology
   for data persistence (mysql, derby, H2, HSQLDB etc).
2. Calculate the following additional metrics (as explained above) and also store them along with the parsed data 
   from the CSV file:
   * CTR
   * CR
   * Fill Rate
   * eCPM
   
   How you store them is completely up to you.
3. Design and expose a RESTful API that can read the reporting data which was parsed and generated in the previous step 
   using the site and month as input arguments. For simplicity, we assume no reporting exists for previous years
   so you don't have to take the year 2018 into account.
4. Use the following keys as the input argument to map to the actual sites:
   * desktop_web: "desktop web"
   * mobile_web: "mobile web"
   * android: "android"
   * iOS: "iOS"
5. Regarding months you must provide a flexible API to accept all of the following options:
   * numeric (ranging from 1-12) that map to the corresponding months (`1` for `January`, `2` for `February` etc)
   * first 3 letters of the month (`Jan` for `January`, `Feb` for `February` etc)
   * full name of the month (case insensitive)
6.  The API must then return a JSON object with all the following metrics defined inside the object itself:
   * month
   * site
   * requests
   * impressions
   * clicks
   * conversions
   * revenue
   * CTR
   * CR
   * fill_rate
   * eCPM
7. Assume a 2-digit precision for all double values.
8. The design of the API is completely up to you. For example, you can either use PathVariable or 
   RequestParam or a combination of the two.
   You can find some examples in the following section.

#### Examples
* GET http://localhost:8080/reports?month=jan&site=desktop_mobile

  or
  
  GET http://localhost:8080/reports/jan/desktop_mobile
  
  or
  
  GET http://localhost:8080/reports/jan?site=desktop_mobile

  JSON Response:
  ``` json
      {
        "month" : "January",
        "site" : "desktop web",
        "requests" : "some_value",
        "impressions" : "some_value",
        "clicks" : "some_value",
        "conversions" : "some_value",
        "revenue" : "some_value",
        "CTR" : "some_value",
        "CR" : "some_value",
        "fill_rate" : "some_value",
        "eCPM" : "some_value"
      }
      ```
   
* GET http://localhost:8080/reports?month=2&site=ios
  JSON Response:
  ``` json
      {
        "month" : "February",
        "site" : "iOS",
        "requests" : "some_value",
        "impressions" : "some_value",
        "clicks" : "some_value",
        "conversions" : "some_value",
        "revenue" : "some_value",
        "CTR" : "some_value",
        "CR" : "some_value",
        "fill_rate" : "some_value",
        "eCPM" : "some_value"
      }
      ```

* Extra points (not mandatory) if you support aggregate reports like
  - GET http://localhost:8080/reports?month=january
  The JSON Response will contain the metrics for January for __all sites__ (notice the absence of "site" attribute):
  ``` json
      {
        "month" : "January",
        "requests" : "some_value",
        "impressions" : "some_value",
        "clicks" : "some_value",
        "conversions" : "some_value",
        "revenue" : "some_value",
        "CTR" : "some_value",
        "CR" : "some_value",
        "fill_rate" : "some_value",
        "eCPM" : "some_value"
      }
  ```
  - GET http://localhost:8080/reports?site=android
  The JSON response will contain the metrics for the android site for __all months__ (notice the absence of "month" attribute):
  ``` json
      {
        "site" : "android",
        "requests" : "some_value",
        "impressions" : "some_value",
        "clicks" : "some_value",
        "conversions" : "some_value",
        "revenue" : "some_value",
        "CTR" : "some_value",
        "CR" : "some_value",
        "fill_rate" : "some_value",
        "eCPM" : "some_value"
      }
  ```
 The above examples are just for guidance, feel free to follow them but don't hesitate to come up with your own approach.
 
====================================================================================================================================
## How to run the solution.

1. Please place the excel file in the location and map the directory to application.properties file.
2. The current port in which the application is running is 7050. You can change it to 8080 in the application.properties file
3. Start the server. 
4. First, we need to parse the csv file and save it in Memory DB (H2).
5. Run - http://localhost:7050/save  --> this will save the data from CSV file to H2 DB.
6. You can check the Data entered in H2 db here -- http://localhost:7050/h2-console (Table name - EXCEL_DATA )
7. Get all the details of the month and site. The API provides a flexibility to enter the month as full Month name (January/February/etc.. )
   and also as number (1/2/3... ) and also first 3 letters of the month (Jan/Feb/Mar / ... ).
    http://localhost:7050/reports/January/iOS
   ```json
    {
        "id": 4,
        "site": "iOS",
        "requests": 2550165,
        "impressions": 2419733,
        "clicks": 6331,
        "conversions": 1564,
        "revenue": 4692.28,
        "month": "January",
        "fillRate": 0,
        "eCPM": 1,
        "ctr": 0,
        "cr": 0
    }
```

      http://localhost:7050/reports/2/android

 ```json
     {
       "id": 7,
        "site": "android",
        "requests": 8921215,
        "impressions": 8342439,
        "clicks": 22934,
        "conversions": 5347,
        "revenue": 17210.12,
        "month": "February",
        "fillRate": 0,
        "eCPM": 2,
        "ctr": 0,
        "cr": 0
     }
 ```

8. The API also provides the functionality for aggregate functionality.

    a. Agrregate by month:
        http://localhost:7050/reports/month/1   or http://localhost:7050/reports/month/jan 
        
```json
     {
        "CTR": 0,
        "conversions": 21406,
        "revenue": 65411.759999999995,
        "month": "January",
        "eCPM": 5,
        "clicks": 86982,
        "fillRate": 0,
        "requests": 34853988,
        "impressions": 33100001,
        "CR": 0
    }
```    
    b. Aggregate by site:
        http://localhost:7050/reports/iOS 
        
``` json
      {
        "CTR": 0,
        "site": "iOS",
        "conversions": 7565,
        "revenue": 16623.65,
        "eCPM": 3,
        "clicks": 25318,
        "fillRate": 0,
        "requests": 7550386,
        "impressions": 6932498,
        "CR": 0
      }
```
        
