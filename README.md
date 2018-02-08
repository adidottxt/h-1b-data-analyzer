Introduction:
===================

This application reads in publicly available H-1B application data (converted into .csv files) from 2008-2017, and analyzes said data to reveal potential insight into the companies, attorneys, universities, cities, states, and employees/jobs (to name a few stakeholders) that are involved in the lottery process that takes place every April. 

You, the user, pick a year, and then pick various questions you’d like to answer about your selected year's data set. You are also given the option to run a simulation of what the visa process would like if all 85,000 visas were handed to the highest bidders (on the basis of wage) as opposed to the luck of the lottery –– and how that affects which cities, states, and companies are more (or less) likely to receive H-1B visas.

First, let’s get you set up.
<br>
<br>
<br>

Setup:
-------------

#### ⬇️ Step 1: 
Download all files found on the H-1B Data Analyzer GitHub page (accessible [here][1].)


#### ⬇️ Step 2:   
Download the full datasets for 2008-2017 (accessible [here][2]) and replace each file in the test-data folder with the datasets available at the link above. Unfortunately with GitHub's restrictions on a repo's storage size, the test-data folder consists of incomplete data with only 1000 cases per year (as opposed to the 150,000-200,000+ cases that are found in the actual datasets.)

#### ➕ Step 3:  
Add all the .jar files found in the lib folder to the libraries in your Java Build Path for whichever SDK you choose to use.

#### 👍🏼 Step 4:  
You’re all set! Run the application via the H1BDataAnalyzer.java file.
<br>
<br>
<br>


How to use the application:
-------------------

#### 🙏🏼 Welcome Page:  
This is what you should see when you open the app. Click the start button to get started.
<br>
#### 📅 Pick Year Page:  
Pick the year you’d like to analyze using the drop down box, and then click the “Confirm Year” button.
<br>
#### 📝 Pick Questions Page(s):  
Done deciding which year you’d like to analyze? Great. One of two pages should pop up, depending on your selection. If you picked a year from 2008-2014, you should see one of two pages. The different pages show up in response to which years’ datasets contain information about the attorneys that filed each H-1B application (2015-2017 does) and which years do not (2008-2014 do not.)

Pick the questions you’d like to get the answer to by checking the corresponding checkbox. Hover over any question to get a brief explanation of what each question refers to. You are free to choose as many as you’d like before clicking the “Confirm Questions” button.

If you pick a question that requires you to pick a city, state, or job, a pop-up window will appear prompting you to make a choice. Make your selection, and then click the “Confirm” button to exit out of the pop-up window.

Click the “Confirm Questions” button when you’re ready to see your answers. If you have not selected any questions, a pop-up window will appear prompting you to select a question.
<br>
#### 📊 Answers page:  
The answers to your selected questions should show up on a window that looks like this:

Scroll down within the text area to view all your answers.

At this point, you are free to either go back and choose different questions (or go back further to choose a different year), or to exit the program using the “Exit” button. 
<br>
<br>
<br>

Potential updates:
-------------------

Potential updates include...
1) adding more simulations to mirror the numerous bills in Congress that propose changes to how H-1B visas are handed out (for example, what would happen if a minimum wage of $130,000 was required?)
2) cleaning up the UI.
3) replacing the current implementation of the final answers with a more visual representation.
<br>
<br>
<br>

Question Explanations
-------------------
Having a hard time understanding what each question means? Try hovering over the question to get a brief explanation of what each question deals with.

Still having trouble? Feel free to peruse the user manul for more in-depth explanations of what insights each question can bring for your selected year.


  [1]: https://github.com/adi-txt/H-1B-Data-Analyzer
  [2]: https://drive.google.com/drive/u/3/folders/1a2kWDqIMqw68YaFk4bnxS-5jmpaak4Rz?usp=sharing
