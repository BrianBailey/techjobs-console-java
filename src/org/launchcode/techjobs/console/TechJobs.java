package org.launchcode.techjobs.console;


// java.util.*;      can use this as well
import java.util.*;



/**
 * Created by LaunchCode
 *
 *
 */

// Tech jobs contains three methods   main,  getUserSelection and printJobs

public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // local variables columnChoices and actionChoices
        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");
        //System.out.println(columnChoices.size());
        //System.out.println(values);

        /*int i = 0;
        System.out.println("\n->Inc/Dec-rementation");
        // The ++ and -- operators increment and decrement by 1 respectively.
        // If they are placed before the variable, they increment then return;
        // after the variable they return then increment.
        System.out.println(i++); // i = 1, prints 0 (post-increment)
        System.out.println(++i); // i = 2, prints 2 (pre-increment)
        System.out.println(i--); // i = 1, prints 2 (post-decrement)
        System.out.println(--i); // i = 0, prints 0 (pre-decrement)
        */


        /*// Convert String To Integer
        System.out.println(sleepIn(false, true));
        System.out.println(sleepIn(true, false));

        System.out.println(sleepIn(false, false));
        */







        // Allow the user to search until they manually quit
        while (true) {
            // first use of getUserSelection
            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {
                // second use of getUserSelection
                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    // Arrays.sort(results);
                    for (String item : results) {
                        // this is bonus... sort alphabetically
                        //String [] itemlist = new String[];





                        //System.out.println(item.toUpperCase());
                        System.out.println(item);
                        //Collections.sort(item);
                        //System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }
    // right-click on the method name , select Find Usages.. displays location where called
    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    //go through an ArrayList of jobs
    //Each job is a Hashmap
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        // keys are the headers
        if (someJobs.size() > 0) {


            for (HashMap<String, String> job : someJobs) {


                String jobInfo = "\n*****\n";


                for (Map.Entry<String, String> jobColumn : job.entrySet()) {
                    jobInfo += (jobColumn.getKey() + ": " + jobColumn.getValue() + "\n");
                }

                jobInfo += "*****" ;
                System.out.println(jobInfo);

            }

        } else {
            System.out.println("No results");
        }




    }

}
