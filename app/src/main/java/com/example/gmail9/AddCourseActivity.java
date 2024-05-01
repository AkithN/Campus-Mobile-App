package com.example.gmail9;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCourseActivity extends AppCompatActivity {

    private EditText courseNameEditText, courseFeeEditText, startingDateEditText, registrationCloseDateEditText, maxParticipantsEditText;
    private Spinner branchesSpinner, durationSpinner;
    private Button addCourseButton, updateCourseButton,buttonClear,buttonSearch,buttonDelete;

    // Database helper
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        // Initialize database helper
        dbHelper = new DBHelper(this);

        // Initialize views
        courseNameEditText = findViewById(R.id.editText_course_name);
        courseFeeEditText = findViewById(R.id.editText_course_fee);
        startingDateEditText = findViewById(R.id.editText_starting_date);
        registrationCloseDateEditText = findViewById(R.id.editText_registration_close_date);
        maxParticipantsEditText = findViewById(R.id.editText_max_participants);
        branchesSpinner = findViewById(R.id.spinner_branches);
        durationSpinner = findViewById(R.id.spinner_duration);
        addCourseButton = findViewById(R.id.b_add);
        updateCourseButton = findViewById(R.id.b_update);
        buttonSearch=findViewById(R.id.b_search);
        buttonDelete=findViewById(R.id.b_delete);
        buttonClear=findViewById(R.id.b_clear);

        // Populate spinners
        ArrayAdapter<CharSequence> branchesAdapter = ArrayAdapter.createFromResource(this,
                R.array.branches, android.R.layout.simple_spinner_item);
        branchesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchesSpinner.setAdapter(branchesAdapter);

        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(this,
                R.array.duration, android.R.layout.simple_spinner_item);
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(durationAdapter);

        // Set onClickListener for add course button
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse();
            }
        });

        // Set onClickListener for update course button
        updateCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCourse();
            }
        });

        //clear all
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        //search
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCourse();
            }
        });

        //Delete Course
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });

        Button backButton = findViewById(R.id.b_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Back to DashBoard
                Intent intent = new Intent(AddCourseActivity.this, AdminActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });


    }

    // Method to add a new course
    private void addCourse() {
        if (isValidInput()) {
            // Get user input
            String courseName = courseNameEditText.getText().toString().trim();
            String courseFee = courseFeeEditText.getText().toString().trim();
            String startingDate = startingDateEditText.getText().toString().trim();
            String registrationCloseDate = registrationCloseDateEditText.getText().toString().trim();
            String maxParticipants = maxParticipantsEditText.getText().toString().trim();
            String selectedBranch = branchesSpinner.getSelectedItem().toString().trim();
            String selectedDuration = durationSpinner.getSelectedItem().toString().trim();

            // Insert course details into the database
            dbHelper.insertDetails(courseName, courseFee, startingDate, registrationCloseDate, maxParticipants, selectedBranch, selectedDuration);

            // Display success message
            Toast.makeText(getApplicationContext(), "Course Added Successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Display error message if input is not valid
            Toast.makeText(getApplicationContext(), "Please fill out all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to update an existing course
    // Method to update an existing course
    private void updateCourse() {
        // Get user input
        String courseName = courseNameEditText.getText().toString().trim();
        String courseFee = courseFeeEditText.getText().toString().trim();
        String startingDate = startingDateEditText.getText().toString().trim();
        String registrationCloseDate = registrationCloseDateEditText.getText().toString().trim();
        String maxParticipants = maxParticipantsEditText.getText().toString().trim();
        String selectedBranch = branchesSpinner.getSelectedItem().toString().trim();
        String selectedDuration = durationSpinner.getSelectedItem().toString().trim();

        // Check if all fields are filled
        if (isValidInput()) {
            // Update course details in the database
            dbHelper.updateCourse(courseName, courseFee, startingDate, registrationCloseDate, maxParticipants, selectedBranch, selectedDuration);

            // Display success message
            Toast.makeText(getApplicationContext(), "Course Updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Display error message if input is not valid
            Toast.makeText(getApplicationContext(), "Please fill out all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to validate input fields
    private boolean isValidInput() {
        // Get user input
        String courseName = courseNameEditText.getText().toString().trim();
        String courseFee = courseFeeEditText.getText().toString().trim();
        String startingDate = startingDateEditText.getText().toString().trim();
        String registrationCloseDate = registrationCloseDateEditText.getText().toString().trim();
        String maxParticipants = maxParticipantsEditText.getText().toString().trim();
        String selectedBranch = branchesSpinner.getSelectedItem().toString().trim();
        String selectedDuration = durationSpinner.getSelectedItem().toString().trim();

        // Check if any of the fields are empty
        return !TextUtils.isEmpty(courseName)
                && !TextUtils.isEmpty(courseFee)
                && !TextUtils.isEmpty(startingDate)
                && !TextUtils.isEmpty(registrationCloseDate)
                && !TextUtils.isEmpty(maxParticipants)
                && !TextUtils.isEmpty(selectedBranch)
                && !TextUtils.isEmpty(selectedDuration);
    }

    //Method all text box clear
    private void  clearAll(){
        courseNameEditText.setText("");
        courseFeeEditText.setText("");
        startingDateEditText.setText("");
        registrationCloseDateEditText.setText("");
        maxParticipantsEditText.setText("");
    }

    // Method to search for a course by name and populate EditText fields
    private void searchCourse() {
        String courseName = courseNameEditText.getText().toString().trim();

        // Check if the course name is provided
        if (!TextUtils.isEmpty(courseName)) {
            // Retrieve course details from the database
            Cursor cursor = dbHelper.getCourseByName(courseName);

            // Check if cursor is not null and contains data
            if (cursor != null && cursor.moveToFirst()) {
                // Populate EditText fields with retrieved data
                courseFeeEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_COURSE_FEE)));
                startingDateEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_START_DATE)));
                registrationCloseDateEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_REGISTRATION_CLOSE_DATE)));
                maxParticipantsEditText.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_MAX_PARTICIPANTS)));

                // Set selected items in Spinners
                String selectedBranch = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SELECTED_BRANCH));
                String selectedDuration = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SELECTED_DURATION));
                // Set selected items in Spinners
                if (selectedBranch != null) {
                    int branchPosition = ((ArrayAdapter) branchesSpinner.getAdapter()).getPosition(selectedBranch);
                    branchesSpinner.setSelection(branchPosition);
                }
                if (selectedDuration != null) {
                    int durationPosition = ((ArrayAdapter) durationSpinner.getAdapter()).getPosition(selectedDuration);
                    durationSpinner.setSelection(durationPosition);
                }

                // Close the cursor
                cursor.close();
            } else {
                // If no course found with the given name, show a toast message
                Toast.makeText(getApplicationContext(), "Course not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // If course name field is empty, show a toast message
            Toast.makeText(getApplicationContext(), "Please enter a course name", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteCourse() {
        String courseName = courseNameEditText.getText().toString().trim();

        // Check if the course name is provided
        if (!TextUtils.isEmpty(courseName)) {
            // Attempt to delete the course from the database
            dbHelper.deleteCourseByName(courseName);

            // Display success message
            Toast.makeText(getApplicationContext(), "Course deleted successfully", Toast.LENGTH_SHORT).show();

            // Clear all fields after deletion
            clearAll();
        } else {
            // If course name field is empty, show a toast message
            Toast.makeText(getApplicationContext(), "Please enter a course name", Toast.LENGTH_SHORT).show();
        }
    }
}