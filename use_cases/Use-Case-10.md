# Use Case 10: Generate City Report 

## Goal in Context: 
- The statistician wants to obtain a clear report on city information for better understanding and analysis. 

## Scope: 
- Population Information. 

## Level: 
- User goal. 

## Preconditions: 
- The system has up-to-date data for cities, including name, country, district, and population. 

## Success Condition: 
- The Statistician successfully generates a city report containing the specified information. 

## Failed Condition: 
- The system fails to generate the city report. 

## Primary Actor: 
- Statistician. 

## Trigger: 

- The statistician requests to generate a city report. 

## Main Success Scenario: 
- The statistician navigates to the report generation section for cities. 
- The system displays options for report parameters (e.g., filters for country or district). 
- The statistician specifies any filters and confirms the report generation. 
- The system retrieves the relevant city data (Name, Country, District, Population). 
- The system generates the report in a specified format (e.g., PDF, CSV). 
- The report is displayed to the statistician or made available for download. 

## Extensions: 
- Data Unavailable: 
  - An error message appears if the city data cannot be retrieved. 
- Format Error:  
  - An error message appears if the chosen report format cannot be generated. 

## Sub-variations: 
- The statistician may filter results by specific criteria (e.g., name, country, and population) 

## Schedule:

Milestones: 
