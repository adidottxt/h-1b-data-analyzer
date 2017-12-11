/**
 * This class is where each H-1B case is stored and later referenced for every method
 * that is part of the H-1B data analyzer program. It stores information relevant to 
 * employers, attorneys, worksites, the offered wage, and the prevailing wage for each
 * application.
 * 
 * @author adi
 *
 */
public class Case {
    
    String employerName;
    String employerState;
    String employerCity;
    
    String attorneyName;
    String[] flippedAttorneyName;
    String attorneyState;
    String attorneyCity;
    
    String worksiteCity;
    String worksiteCounty;
    String worksiteState;
    
    String jobTitle;
    
    double wageRate;
    double prevailingWage;
    int wageDifference;
        
    public Case (String[] row, int year) {
        
        if (year == 2008) {
                  
              if (row[3] != null) {
                  employerName = row[3].toString().toUpperCase();
              }
              
              if (row[6] != null) {
                  employerCity = row[6].toString().trim().replaceAll(",", "");
              } 
              
              if (row[7] != null) {
                  employerState = row[7].toString();
              }

              if (row[12] != null) {
                  jobTitle = row[12].toString();
              }
        
              if (row[18] != null) {
                  wageRate = Double.parseDouble(row[18].toString().trim());
                  if (employerCity.contains("_") || wageRate < 5000) {
                      wageRate = 50000;
                  }
              }
        
              if (row[22] != null) {
                  worksiteCity = row[22].toString();
              }
        
              if (row[23] != null) {
                  worksiteState = row[23].toString();
              }
        
              if (row[24] != null) {
                  prevailingWage = Double.parseDouble(row[24].toString().trim());
              }
   
              wageDifference = (int) (wageRate - prevailingWage);
              
              if (row[6].toString().length() < 3 || row[6].toString().matches(".*\\d+.*")) {
                  wageDifference = 0;
              }
            
                    
        } else if (year == 2009) {
                
            if (row[3] != null) {
                employerName = row[3].toString();
            }
            
            if (row[6] != null) {
                employerCity = row[6].toString().replaceAll(",", "");
            } 
            
            if (row[7] != null) {
                employerState = row[7].toString();
            }
            
            if (row[13] != null) {
                jobTitle = row[13].toString();
            }
      
            if (row[18] != null) {
                wageRate = Double.parseDouble(row[18].toString().trim());
                if (employerCity.contains("_") || wageRate < 5000) {
                    wageRate = 50000;
                }
            }
      
            if (row[22] != null) {
                worksiteCity = row[22].toString();
            }
      
            if (row[23] != null) {
                worksiteState = row[23].toString();
            }
      
            if (row[24] != null) {
                prevailingWage = Double.parseDouble(row[24].toString().trim());
                if (employerCity.contains("_")) {
                    prevailingWage = 50000;
                }
            }
 
            wageDifference = (int) (wageRate - prevailingWage);
  
            if (employerCity.equals("CITY") || employerCity.equals("CITY:")) {
                wageDifference = 0;
            }
            
            if (row[6].toString().length() < 3 || row[6].toString().matches(".*\\d+.*")) {
                wageDifference = 0;
            }
        }

        else if (year == 2010) {
                
            if (row[6] != null) {
                employerName = row[6].toString();
            }
            
            if (row[9] != null) {
                employerCity = row[9].toString().replaceAll(",", "");
            } 
            
            if (row[10] != null) {
                employerState = row[10].toString();
            }

            if (row[14] != null) {
                jobTitle = row[14].toString();
            }
      
            if (row[15] != null && row[15].length() != 0) {
                wageRate = Double.parseDouble(row[15].toString().trim());
                if (employerCity.contains("_") || wageRate < 5000) {
                    wageRate = 50000;
                }
            }
      
            if (row[18] != null) {
                worksiteCity = row[18].toString();
            }
      
            if (row[19] != null) {
                worksiteState = row[19].toString();
            }
      
            if (row[20] != null && row[20].length() != 0) {
                prevailingWage = Double.parseDouble(row[20].trim());
                if (employerCity.contains("_")) {
                    prevailingWage = 50000;
                }
            }
 
            wageDifference = (int) (wageRate - prevailingWage);
  
            if (employerCity.equals("CITY") || employerCity.equals("CITY:")) {
                wageDifference = 0;
            } 
            
            if (row[9].toString().length() < 3 || row[9].toString().matches(".*\\d+.*")) {
                wageDifference = 0;
            }
        }
        
        else if (year == 2011 || year == 2012 || year == 2013 || year == 2014) {
                
            if (row[7] != null) {
                employerName = row[7].toString();
            }
            
            if (row[9] != null) {
                employerCity = row[9].toString().replace(",", "");
            } 
            
            if (row[10] != null) {
                employerState = row[10].toString();
            }

            if (row[14] != null) {
                jobTitle = row[14].toString();
            }
      
            if (row[15] != null && row[15].length() != 0) {
                wageRate = Double.parseDouble(row[15].toString().trim());
                if (employerCity.contains("_") || wageRate < 5000) {
                    wageRate = 50000;
                }
            }
      
            if (row[20] != null) {
                worksiteCity = row[20].toString();
            }
      
            if (row[21] != null) {
                worksiteState = row[21].toString();
            }
      
            if (row[22] != null && row[22].length() != 0 && row[22].length() != 1) {
                if (!row[22].contains("-") && !row[22].contains("/")) {
                    prevailingWage = Double.parseDouble(row[22].trim().replaceAll(",", ""));
                    if (employerCity.contains("_")) {
                        prevailingWage = 0;
                    }
                }
            }
 
            wageDifference = (int) (wageRate - prevailingWage);
            
            if (row[9].toString().length() < 3 || row[9].toString().matches(".*\\d+.*")) {
                wageDifference = 0;
            } 
        }   
        
        else if (year == 2015) {

            if (row[7] != null) {
                employerName = row[7].toString();
            }
            
            if (row[10] != null) {
                employerCity = row[10].toString().replaceAll(",", "");
            } 
            
            if (row[11] != null) {
                employerState = row[11].toString();
            }
            
            if (row[17] != null) {
                attorneyName = row[17].toString();
            }
            
            if (row[18] != null) {
                attorneyCity = row[18].toString();
            }
            
            if (row[19] != null) {
                attorneyState = row[19].toString();
            }
            
            if (row[20] != null) {
                jobTitle = row[20].toString();
            }
            
            if (row[26] != null && row[26].length() != 0 && row[26].length() != 1) {
                if (!row[26].contains("-") && !row[26].contains("/")) {
                    prevailingWage = Double.parseDouble(row[26].trim().replaceAll(",", ""));
                    if (employerCity.contains("_")) {
                        prevailingWage = 50000;
                    }
                }
            }
            
            if (row[32] != null && row[32].length() != 0 && row[32].length() != 1) {
                wageRate = Double.parseDouble(row[32].split("-")[0].trim());
                if (employerCity.contains("_") || wageRate < 5000) {
                    wageRate = 50000;
                }
            }
      
            if (row[36] != null) {
                worksiteCity = row[36].toString();
            }
            
            if (row[38] != null) {
                worksiteState = row[38].toString();
            }

            wageDifference = (int) (wageRate - prevailingWage);
            
            if (row[10].toString().length() < 3 || row[10].toString().matches(".*\\d+.*")) {
                    wageDifference = 0;
            }
   
        } else if (year == 2016) {
                
            if (row[7] != null) {
                employerName = row[7].toString();
            }
            
            if (row[9] != null) {
                employerCity = row[9].toString().replaceAll(",", "");
            } 
            
            if (row[10] != null) {
                employerState = row[10].toString();
            }
      
            if (row[16] != null) {
                if (row[16].toString().length() > 1) {
                    flippedAttorneyName = row[16].toString().split(",");
                    attorneyName = flippedAttorneyName[1].toString().trim() + " "
                             + flippedAttorneyName[0].toString().trim();
                } else {
                    attorneyName = "";
                }
            }
            
            if (row[17] != null) {
                attorneyCity = row[17].toString();
            }
            
            if (row[18] != null) {
                attorneyState = row[18].toString();
            }
            
            if (row[19] != null) {
                jobTitle = row[19].toString();
            }
            
            if (row[25] != null && row[25].length() != 0 && row[25].length() != 1) {
                if (!row[25].contains("-") && !row[25].contains("/")) {
                    prevailingWage = Double.parseDouble(row[25].trim().replaceAll(",", ""));
                    if (employerCity.contains("_")) {
                        prevailingWage = 50000;
                    }
                }
            }
            
            if (row[30] != null && row[30].length() != 0 && row[30].length() != 1) {
                wageRate = Double.parseDouble(row[30].split("-")[0].replaceAll(",", "").trim());
                if (employerCity.contains("_") || wageRate < 5000) {
                    wageRate = 50000;
                }
            }
      
            if (row[35] != null) {
                worksiteCity = row[35].toString();
            }
            
            if (row[37] != null) {
                worksiteState = row[37].toString();
            }

            wageDifference = (int) (wageRate - prevailingWage);
            
            if (row[9].toString().length() < 3 || row[9].toString().matches(".*\\d+.*")) {
                wageDifference = 0;
            }
            
        } else if (year == 2017) {
            
            if (row[7] != null) {
                employerName = row[7].toString();
            }
            
            if (row[10] != null) {
                employerCity = row[10].toString().replaceAll(",", "");
            } 
            
            if (row[11] != null) {
                employerState = row[11].toString();
            }
      
            if (row[18] != null) {
                if (row[18].toString().length() > 1) {
                    flippedAttorneyName = row[18].toString().split(",");
                    attorneyName = flippedAttorneyName[1].toString().trim() + " "
                             + flippedAttorneyName[0].toString().trim();
                } else {
                    attorneyName = "";
                }
            }
            
            if (row[19] != null) {
                attorneyCity = row[19].toString();
            }
            
            if (row[20] != null) {
                attorneyState = row[20].toString();
            }
            
            if (row[21] != null) {
                jobTitle = row[21].toString();
            }
            
            if (row[33] != null && row[33].length() != 0 && row[33].length() != 1) {
                if (!row[33].contains("-") && !row[33].contains("/")) {
                    prevailingWage = Double.parseDouble(row[33].trim().replaceAll(",", ""));
                    if (employerCity.contains("_")) {
                        prevailingWage = 50000;
                    }
                }
            }
            
            if (row[39] != null && row[39].length() != 0 && row[39].length() != 1) {
                wageRate = Double.parseDouble(row[39].split("-")[0].replaceAll(",", "").trim());
                if (employerCity.contains("_") || wageRate < 5000) {
                    wageRate = 50000;
                }
            }
      
            if (row[47] != null) {
                worksiteCity = row[47].toString();
            }
            
            if (row[49] != null) {
                worksiteState = row[49].toString();
            }
            
            wageDifference = (int) (wageRate - prevailingWage);
            
            if (row[10].toString().length() < 3 || row[10].toString().matches(".*\\d+.*")) {
                wageDifference = 0;
            }
        }
    }
}
