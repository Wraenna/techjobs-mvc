package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String searchByColumn(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        /**
         * The all-search is a copy-paste from TechJobs Console.
         * Differences: the ArrayList is built using a public method from JobData called findAll()
         */

        ArrayList<HashMap<String, String>> jobs = JobData.findAll();
        ArrayList<HashMap<String, String>> found_jobs = new ArrayList<>();

        searchTerm = searchTerm.toLowerCase();

        if (searchType.equals("all")) {

            for (HashMap<String, String> row: jobs) {
                for (String column : row.keySet()) {

                    String aValue = row.get(column);
                    aValue = aValue.toLowerCase();

                    if (aValue.contains(searchTerm) && !found_jobs.contains(row)) {
                        found_jobs.add(row);
                    }
                }

            }
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("displayJobs", found_jobs);
            return "search";
        } else {

            for (HashMap<String, String> row : jobs) {

                String aValue = row.get(searchType);
                aValue = aValue.toLowerCase();

                if (aValue.contains(searchTerm)) {
                    found_jobs.add(row);
                }
            }


            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("displayJobs", found_jobs);
            return "search";
        }
    }

}
