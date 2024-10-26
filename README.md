# Group One DevOps project

* Workflows ![workflow](https://github.com/TvonSween/sem-one/actions/workflows/main.yml/badge.svg)
* Master ![Master build status ](https://img.shields.io/github/actions/workflow/status/TvonSween/sem-one/main.yml?branch=master)
* Develop ![Develop build status ](https://img.shields.io/github/actions/workflow/status/TvonSween/sem-one/main.yml?branch=develop)
* [![LICENSE](https://img.shields.io/github/license/TvonSween/sem-one.svg?style=flat-square)](https://github.com/TvonSween/sem-one/blob/master/LICENSE)
* [![Releases](https://img.shields.io/github/release/TvonSween/sem-one/all.svg?style=flat-square)](https://github.com/TvonSween/sem-one/releases)

## Members
* Kemar Hinds  - Developer
* Shannon Kane  - Scrum Master
* Nikolaos Koikas  - Developer
* Cam McVey  - Product Owner
* Danny Mutale  - Developer
* Tess Vaughan - Developer

## [Code of Conduct](CODE_OF_CONDUCT.md)


## Requirements Table
| ID | Name                                                                                                  | Met | Screenshot                                                                      | Link                                                                           |
|----|-------------------------------------------------------------------------------------------------------|-----|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| 1  | All the countries in the world organised by largest population to smallest.                           | Yes | ![PopulationOfCountries](img/PopulationOfCountries.png)                         | [PopulationOfCountries](tmp/PopulationOfCountries.csv)                         |
| 2  | All the countries in a continent organised by largest population to smallest.                         | No  | insert Image                                                                    | insert Link                                                                    |
| 3  | All the countries in a region organised by largest population to smallest.                            | No  | ![Region-CountryByLargestPopulation](img/Region-CountrybyLargestPopulation.png) | [Region-CountrybyLargestPopulation](tmp/Region-CountrybyLargestPopulation.csv) |
| 4  | The top N populated countries in the world where N is provided by the user.                           | Yes | ![TopCountriesPerPopulation](img/TopCountriesPerPopulation.png)                 | [TopCountriesPerPopulation](tmp/TopCountriesPerPopulation.csv)                 |
| 5  | The top N populated countries in a continent where N is provided by the user.                         | No  | insert image                                                                    | insert Link                                                                    |
| 6  | The top N populated countries in a region where N is provided by the user.                            | No  | insert image                                                                    |
| 7  | All the cities in the world organised by largest population to smallest.                              | No  | insert image                                                                    |
| 8  | All the cities in a continent organised by largest population to smallest.                            | No  | insert image                                                                    |
| 9  | All the cities in a region organised by largest population to smallest.                               | No  | insert image                                                                    |
| 10 | All the cities in a country organised by largest population to smallest.                              | No  | insert image                                                                    |
| 11 | All the cities in a district organised by largest population to smallest.                             | No  | insert image                                                                    |
| 12 | The top N populated cities in the world where N is provided by the user.                              | No  | insert image                                                                    |
| 13 | The top N populated cities in a continent where N is provided by the user.                            | No  | insert image                                                                    |
| 14 | The top N populated cities in a region where N is provided by the user.                               | No  | insert image                                                                    |
| 15 | The top N populated cities in a country where N is provided by the user.                              | No  | insert image                                                                    |
| 16 | The top N populated cities in a district where N is provided by the user.                             | No  | insert image                                                                    |
| 17 | All the capital cities in the world organised by largest population to smallest.                      | No  | insert image                                                                    |
| 18 | All the capital cities in a continent organised by largest population to smallest.                    | No  | insert image                                                                    |
| 19 | All the capital cities in a region organised by largest to smallest.                                  | No  | insert image                                                                    |
| 20 | The top N populated capital cities in the world where N is provided by the user.                      | No  | insert image                                                                    |
| 21 | The top N populated capital cities in a continent where N is provided by the user.                    | No  | insert image                                                                    |
| 22 | The top N populated capital cities in a region where N is provided by the user.                       | No  | insert image                                                                    |
| 23 | The population of people, people living in cities, and people not living in cities in each continent. | No  | insert image                                                                    |
| 24 | The population of people, people living in cities, and people not living in cities in each region.    | No  | insert image                                                                    |
| 25 | The population of people, people living in cities, and people not living in cities in each country.   | No  | insert image                                                                    |
| 26 | The population of the world.                                                                          | No  | insert image                                                                    |
| 27 | The population of a continent.                                                                        | No  | insert image                                                                    |
| 28 | The population of a region.                                                                           | No  | insert image                                                                    |
| 29 | The population of a country.                                                                          | No  | insert image                                                                    |
| 30 | The population of a district.                                                                         | No  | insert image                                                                    |
| 31 | The population of a city.                                                                             | No  | insert image                                                                    |
| 32 | Report for specific languages in population, greatest to smallest with % of world population.         | No  | insert image                                                                    |

## Workflow
* Decide which user story to work on for next sprint
* Create new sprint/assign to sprint on Zube
* Add user story card(s) to Ready column in Zube
* Add any additional task cards to Zube and put in priority order
* Pull the latest develop branch. 
* Start a new feature branch for the user story/task
* Select task to work on in Zube
* Work on task
* Once feature is finished, create JAR file. 
* Update and test Docker configuration with Travis
* Update feature branch with develop to ensure feature is up-to-date. 
* Check feature branch still works. 
* Merge feature branch into develop. 
* Repeat 5-14 until release is ready. 
* Merge develop branch into release and create release.
* Merge release into master and develop.
* Close the sprint
