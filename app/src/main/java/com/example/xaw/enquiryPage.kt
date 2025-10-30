package com.example.xaw

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.*
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class enquiryPage : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etLName: EditText
    private lateinit var etPhone: EditText
    private lateinit var numberSpinner: Spinner
    private lateinit var courseContainer: LinearLayout
    private lateinit var btnSubmit: Button

    private val SMS_PERMISSION_CODE = 100

    // Course options and prices
    private val courses = listOf(
        "Sewing",
        "Child Minding",
        "Cooking",
        "Gardening",
        "Landscaping",
        "Life Skills",
        "First Aid"
    )

    private val coursePrices = mapOf(
        "Sewing" to 1500,
        "Landscaping" to 1500,
        "Life Skills" to 1500,
        "First Aid" to 1500,
        "Gardening" to 750,
        "Child Minding" to 750,
        "Cooking" to 750
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enquiry_page)

        // Link UI components
        etName = findViewById(R.id.etName)
        etLName = findViewById(R.id.etLName)
        etPhone = findViewById(R.id.etNumber)
        numberSpinner = findViewById(R.id.numberSpinner)
        courseContainer = findViewById(R.id.courseContainer)
        btnSubmit = findViewById(R.id.btnSubmit)

        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val pricesBtn = findViewById<ImageButton>(R.id.pricesBtn)
        val enquireBtn = findViewById<ImageButton>(R.id.enquireBtn)
        val logoutBtn = findViewById<Button>(R.id.logOutBtn)

        logoutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // --- Number of courses Spinner ---
        val numbers = (1..7).map { it.toString() }
        val numberAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numbers)
        numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        numberSpinner.adapter = numberAdapter

        // Dynamically add course Spinners based on number selection
        numberSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val numCourses = numbers[position].toInt()
                courseContainer.removeAllViews() // clear previous spinners

                for (i in 1..numCourses) {
                    val spinner = Spinner(this@enquiryPage)
                    val adapter = ArrayAdapter(this@enquiryPage, android.R.layout.simple_spinner_item, courses)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    // Optional spacing
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 0, 0, 20)
                    spinner.layoutParams = params

                    courseContainer.addView(spinner)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                courseContainer.removeAllViews()
            }
        }

        // --- Navigation buttons ---
        homeBtn.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        pricesBtn.setOnClickListener {
            val intent = Intent(this, CoursesScreen::class.java)
            startActivity(intent)
        }

        enquireBtn.setOnClickListener {
            val intent = Intent(this, enquiryPage::class.java)
            startActivity(intent)
        }

        // --- Submit button ---
        btnSubmit.setOnClickListener {
            //sendEnquirySms()
            val phone = etPhone.text.toString().trim()

            // For demonstration: hardcode example details or replace with actual data later
            val name = "Example User"
            val courses = "Gardening, Cooking"
            val total = "R2250"

            if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            } else {
                val smsBody = "Name: $name\nCourses: $courses\nTotal Fees: $total"

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("sms:$phone")
                intent.putExtra("sms_body", smsBody)

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "No messaging app found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // --- Send SMS with all selected courses ---
    private fun sendEnquirySms() {
        val firstName = etName.text.toString().trim()
        val lastName = etLName.text.toString().trim()
        val phone = etPhone.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            return
        }

        // Collect all selected courses
        val selectedCourses = mutableListOf<String>()
        for (i in 0 until courseContainer.childCount) {
            val spinner = courseContainer.getChildAt(i) as Spinner
            selectedCourses.add(spinner.selectedItem.toString())
        }

        if (selectedCourses.isEmpty()) {
            Toast.makeText(this, "Please select at least one course.", Toast.LENGTH_SHORT).show()
            return
        }

        val numCourses = selectedCourses.size

        // Calculate total price
        val totalBeforeDiscount = selectedCourses.sumOf { coursePrices[it] ?: 0 }

        // Determine discount
        val discountPercentage = when (numCourses) {
            2 -> 0.05
            3 -> 0.10
            else -> if (numCourses >= 4) 0.15 else 0.0
        }
        val discountAmount = totalBeforeDiscount * discountPercentage
        val totalAfterDiscount = totalBeforeDiscount - discountAmount

        val formattedBefore = String.format("%.2f", totalBeforeDiscount)
        val formattedAfter = String.format("%.2f", totalAfterDiscount)
        val formattedDiscount = (discountPercentage * 100).toInt()

        val coursesList = selectedCourses.joinToString(", ")

        val message = """
        Thank you, $firstName $lastName!
        You have chosen $numCourses course(s): $coursesList.
        
        Original total: R$formattedBefore
        Discount applied: $formattedDiscount%
        Total after discount: R$formattedAfter
        
        We’ll be in touch with more details soon.
        – Empower The Nation
    """.trimIndent()

        // --- Open SMS app with message ready ---
        val smsIntent = Intent(Intent.ACTION_VIEW).apply {
            data = android.net.Uri.parse("smsto:$phone")
            putExtra("sms_body", message)
        }

        try {
            startActivity(smsIntent)
            Toast.makeText(this, "SMS app opened with your enquiry.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to open SMS app: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted to send SMS.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "SMS permission denied.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
