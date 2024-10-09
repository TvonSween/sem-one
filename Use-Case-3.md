# Use Case 2: View Countries by Continent

## Goal in Context: 
- The statistician wants to analyze population distribution within a specific continent. 

## Scope: 
- Population Information. 

## Level: 
- User goal. 

## Preconditions: 
- The system has up-to-date population data for all countries, organized by continent. 

## Success Condition: 
- The statistician views all countries in a selected continent sorted by population from largest to smallest. 

## Failed Condition: 
- The statistician is unable to retrieve the list due to data issues. 

## Primary Actor: 
- Statistician. 

## Trigger: 
- The statistician selects a continent from the menu to view its countries by population. 

## Main Success Scenario: 
- The statistician selects a continent. 
- The system retrieves and sorts the countries in that continent by population. 
- The sorted list is displayed to the statistician. 

## Extensions: 
 ## Data Unavailable: 
    - An error message is shown if data cannot be retrieved for the selected continent. 

## Sub-variations: 
- The statistician may choose to view additional data such as capital cities and cities. 

## Schedule: 
