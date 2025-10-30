package com.example.xaw

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)

        // Bottom navigation
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

        // 🔹 Social buttons
        val btnYouTube = findViewById<ImageButton>(R.id.btnYouTube)
        val btnTwitter = findViewById<ImageButton>(R.id.btnTwitter)
        val btnTikTok = findViewById<ImageButton>(R.id.btnTikTok)
        val btnFacebook = findViewById<ImageButton>(R.id.btnFacebook)
        val btnInstagram = findViewById<ImageButton>(R.id.btnInstagram)

        btnYouTube.setOnClickListener {
            openLink("https://www.youtube.com/@empoweringthenation-k2q")
        }

        btnTwitter.setOnClickListener {
            openLink("https://x.com/empowering11276?s=21")
        }

        btnTikTok.setOnClickListener {
            openLink("https://www.tiktok.com/@empowering.the.na4?_t=ZS-90k6R4PiuNo&_r=1")
        }

        btnFacebook.setOnClickListener {
            openLink("https://www.facebook.com/people/Empowering-the-Nation/61579850893491/?mibextid=wwXIfr&rdid=SffEcW6kivWJSzoA&share_url=https%3A%2F%2Fwww.facebook.com%2Fshare%2F1BFicVpBtP%2F%3Fmibextid%3DwwXIfr")
        }

        btnInstagram.setOnClickListener {
            openLink("https://www.instagram.com/empowering_the_nation__/?igsh=cjI2cTdtNGFtNnZ4&utm_source=qr")
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
