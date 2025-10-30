package com.example.xaw

import android.os.Bundle
import android.widget.ArrayAdapter
import android.content.Intent
import android.widget.ImageButton
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class CoursesScreen : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var title: TextView
    private lateinit var fees: TextView
    private lateinit var purpose: TextView
    private lateinit var topics: TextView
    private lateinit var button: Button
    private lateinit var courseImage: ImageView  // Added for course image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_courses_screen)

        val homeBtn = findViewById<ImageButton>(R.id.homeBtn)
        val pricesBtn = findViewById<ImageButton>(R.id.pricesBtn)
        val enquireBtn = findViewById<ImageButton>(R.id.enquireBtn)

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

        spinner = findViewById(R.id.course_spinner)
        title = findViewById(R.id.course_title)
        fees = findViewById(R.id.course_fees)
        purpose = findViewById(R.id.course_purpose)
        topics = findViewById(R.id.course_topics)
        button = findViewById(R.id.view_courses_btn)
        courseImage = findViewById(R.id.courseImage) // Reference to the ImageView

        val courses = listOf("Select a course", "Sewing", "Child Minding", "Cooking", "Gardening", "Landscaping", "Life Skills", "First Aid")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selected = courses[position]
                when (selected) {
                    "Sewing" -> {
                        title.text = "Sewing"
                        fees.text = "R1 500 per month"
                        purpose.text = "To provide alterations and new garment tailoring services."
                        topics.text =
                            "• Types of stitches\n• Threading a sewing machine\n• Sewing buttons, zips, hems and seams\n• Alterations\n• Designing and sewing new garments"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.sewing) // Image for cooking
                    }

                    "Cooking" -> {
                        title.text = "Cooking"
                        fees.text = "R750 per month"
                        purpose.text = "To prepare and cook nutritious family meals."
                        topics.text =
                            "• Nutritional requirements for a healthy body\n• Types of protein, carbohydrates and vegetables\n• Planning meals\n• Tasty and nutritious recipes\n• Preparation and cooking of meals"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.cooking) // Image for cooking
                    }

                    "Child Minding" -> {
                        title.text = "Child Minding"
                        fees.text = "R750 per month"
                        purpose.text = "To provide basic child and baby care."
                        topics.text =
                            "• Birth to six-month old baby needs\n• Seven-month to one year old needs\n• Toddler needs\n• Educational Toys"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.child) // Image for Child Minding
                    }

                    "Gardening" -> {
                        title.text = "Gardening"
                        fees.text = "R750 per month"
                        purpose.text =
                            "To provide basic knowledge of watering, pruning and planting in a domestic garden."
                        topics.text =
                            "• Water restrictions and the watering requirements of indigenous and exotic plants\n• Pruning and propagation of plants\n• Planting techniques for different plant types"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.gardening) // Image for gardening
                    }

                    "Landscaping" -> {
                        title.text = "Landscaping"
                        fees.text = "R1 500 per month"
                        purpose.text =
                            "To provide landscaping services for new and established gardens."
                        topics.text =
                            "• Indigenous and exotic plants and trees\n• Fixed structures (fountains, statues, benches, tables, built-on braai)\n• Balancing of plants and trees in a garden\n• Aesthetics of plant shapes and colours\n•Garden layout"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.landscaping) // Image for landscaping
                    }

                    "Life Skills" -> {
                        title.text = "Life Skills"
                        fees.text = "R1 500 per month"
                        purpose.text = "To provide skills to navigate basic life necessities."
                        topics.text =
                            "• Opening a bank account\n• Basic labour law (know your rights)\n• Basic reading and writing literacy\n• Basic numeric literacy"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.lifeskills) // Image for life skills
                    }

                    "First Aid" -> {
                        title.text = "First Aid"
                        fees.text = "R1 500 per month"
                        purpose.text = "To provide first aid awareness and basic life support."
                        topics.text =
                            "• Wounds and bleeding\n• Burns and fractures\n• Emergency scene management\n• Cardio-Pulmonary Resuscitation\n• Respiratory distress e.g. Choking, blocked airway"
                        button.text = "Enquire about courses"
                        courseImage.setImageResource(R.drawable.firstaid) // Image for first aid

                    }

                    else -> {
                        title.text = ""
                        fees.text = ""
                        purpose.text = ""
                        topics.text = ""
                        button.text = ""
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        button.setOnClickListener {
            val intent = Intent(this, enquiryPage::class.java)
            startActivity(intent)
        }
    }
}