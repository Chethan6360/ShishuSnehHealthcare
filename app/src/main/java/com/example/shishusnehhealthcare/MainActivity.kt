package com.example.shishusnehhealthcare

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

data class GrowthRecord(
    val month: String,
    val weight: String,
    val height: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShishuSnehNavigation()
        }
    }
}

@Composable
fun ShishuSnehApp(navController: NavHostController) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp,
            bottom = 100.dp
        ),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Button(
                onClick = {
                    navController.navigate("profile")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Baby Profile")
            }
        }

        item { DashboardCard(navController) }
        item { GrowthTrackerCard(navController) }
        item { VaccinationCard(navController) }
        item {
            Button(
                onClick = {
                    navController.navigate("feeding_guide")
                }
            ) {
                Text("Feeding Guide")
            }
        }

        item {
            Button(
                onClick = {
                    navController.navigate("milestone")
                }
            ) {
                Text("Milestone Tracker")
            }
        }

        item {
            Button(
                onClick = {
                    navController.navigate("emergency")
                }
            ) {
                Text("Emergency Contact")
            }
        }
    }
}

@Composable
fun DashboardCard(navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F5E9)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Mother & Baby Dashboard",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF1B5E20)
            )
            val context = LocalContext.current

            val sharedPreferences =
                context.getSharedPreferences(
                    "ChildProfile",
                    Context.MODE_PRIVATE
                )

            val savedBabyName =
                sharedPreferences.getString(
                    "babyName",
                    "No Name"
                )

            val savedBabyAge =
                sharedPreferences.getString(
                    "babyAge",
                    "0 Months"
                )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Baby Name: $savedBabyName",
                fontSize = 16.sp
            )

            Text(
                text = "Baby Age: $savedBabyAge",
                fontSize = 16.sp
            )

            Text(
                text = "Health Status: Healthy",
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate("health_report")
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("View Health Report")
            }
        }
    }
}

@Composable
fun GrowthTrackerCard(navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {


            Text(
                text = "Growth Tracker",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Weight: 7.2 kg")
            Text("Height: 65 cm")
            Text("Monthly Growth: Normal")

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("growth_tracker")
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Track Growth")
            }
        }
    }
}

@Composable
fun VaccinationCard(navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF3E0)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Vaccination Reminder",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFFE65100)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Next Vaccine: Polio")
            Text("Date: 20 May 2026")
            Text("Status: Upcoming")

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    navController.navigate("vaccination")
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("View Schedule")
            }
        }
    }
}

@Composable
fun FeedingGuideScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Feeding Guide",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("0–6 Months: Breast milk only")
        Spacer(modifier = Modifier.height(8.dp))

        Text("6–12 Months: Soft foods, fruits, mashed rice")
        Spacer(modifier = Modifier.height(8.dp))

        Text("1–2 Years: Vegetables, milk, eggs, fruits")
        Spacer(modifier = Modifier.height(8.dp))

        Text("2+ Years: Balanced healthy diet")
    }
}

@Composable
fun MilestoneScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Milestone Tracker",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("2 Months: Smiles")
        Text("4 Months: Holds head up")
        Text("6 Months: Rolls over")
        Text("9 Months: Crawling")
        Text("12 Months: Walking")
    }
}

@Composable
fun EmergencyScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Emergency Contacts",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Hospital: 108")
        Text("Ambulance: 102")
        Text("Pediatrician: +91 XXXXX XXXXX")
    }
}

@Composable
fun HealthReportScreen(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences("ChildProfile", Context.MODE_PRIVATE)

    val babyName =
        sharedPreferences.getString("babyName", "") ?: ""

    val babyDOB =
        sharedPreferences.getString("babyDOB", "") ?: ""

    val phoneNumber =
        sharedPreferences.getString("phoneNumber", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Health Report",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Baby Name: $babyName")
                Text("Date of Birth: $babyDOB")
                Text("Phone Number: $phoneNumber")
                Text("Health Status: Healthy")
            }
        }
    }
}



@Composable
fun GrowthScreen() {
    Text("Growth Screen")
}

@Composable
fun VaccinationScreen() {
    Text("Vaccination Screen")
}

@Composable
fun FeedingScreen() {
    Text("Feeding Guide Screen")
}

@Composable
fun MilestoneScreen() {
    Text("Milestone Screen")
}

@Composable
fun EmergencyScreen() {
    Text("Emergency Screen")
}

@Composable
fun LoginScreen(
    navController: NavHostController
) {

    val context = androidx.compose.ui.platform.LocalContext.current

    val sharedPreferences =
        context.getSharedPreferences(
            "ShishuSnehPrefs",
            Context.MODE_PRIVATE
        )

    var motherName by remember {
        mutableStateOf(
            sharedPreferences.getString(
                "mother_name",
                ""
            ) ?: ""
        )
    }

    var mobileNumber by remember {
        mutableStateOf(
            sharedPreferences.getString(
                "mobile_number",
                ""
            ) ?: ""
        )
    }

    var showError by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ShishuSneh Healthcare",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = motherName,
            onValueChange = {
                motherName = it
            },
            label = {
                Text("Mother Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                mobileNumber = it
            },
            label = {
                Text("Mobile Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (showError) {
            Text(
                text = "Please enter all details",
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (
                    motherName.isNotBlank() &&
                    mobileNumber.isNotBlank()
                ) {

                    sharedPreferences.edit()
                        .putString(
                            "mother_name",
                            motherName
                        )
                        .putString(
                            "mobile_number",
                            mobileNumber
                        )
                        .apply()

                    ReminderScheduler(context)
                        .scheduleVaccineReminder()

                    navController.navigate("home")

                } else {
                    showError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Composable
fun VaccinationReminderScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(
            "ChildProfile",
            Context.MODE_PRIVATE
        )

    val babyDOB =
        sharedPreferences.getString(
            "babyDOB",
            ""
        ) ?: ""

    var nextVaccine = "BCG"
    var vaccineDate = "At Birth"

    if (babyDOB.isNotEmpty()) {
        nextVaccine = "Polio"

        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy")
        val dob = sdf.parse(babyDOB)

        val calendar = java.util.Calendar.getInstance()
        calendar.time = dob!!
        calendar.add(java.util.Calendar.WEEK_OF_YEAR, 6)

        vaccineDate = sdf.format(calendar.time)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(
            onClick = {
                NotificationHelper(context).showNotification()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Vaccine Reminder")
        }

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("← Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Vaccination Reminder",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        VaccinationCardItem(
            vaccineName = "BCG",
            dueDate = "Completed",
            status = "✅ Done"
        )

        VaccinationCardItem(
            vaccineName = nextVaccine,
            dueDate = vaccineDate,
            status = "⏳ Upcoming"
        )

        VaccinationCardItem(
            vaccineName = "Hepatitis B",
            dueDate = "28 May 2026",
            status = "⏳ Upcoming"
        )

        VaccinationCardItem(
            vaccineName = "MMR",
            dueDate = "15 June 2026",
            status = "📅 Scheduled"
        )
    }
}

@Composable
fun VaccinationCardItem(
    vaccineName: String,
    dueDate: String,
    status: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = vaccineName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Due Date: $dueDate",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Status: $status",
                fontSize = 16.sp
            )
        }
    }
}



@Composable
fun GrowthChartScreen(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(
            "GrowthData",
            Context.MODE_PRIVATE
        )


    // ---------- STATES ----------
    var month by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    val growthHistory = remember {
        mutableStateListOf<GrowthRecord>()
    }


// ---------- CHART DATA ----------
    val weightEntries = remember {
        mutableStateListOf<Entry>()
    }

    val heightEntries = remember {
        mutableStateListOf<Entry>()
    }

    val weightDataSet = remember {
        LineDataSet(weightEntries, "Weight (kg)").apply {
            color = android.graphics.Color.BLUE
            setCircleColor(android.graphics.Color.BLUE)
            lineWidth = 2f
            circleRadius = 5f
            valueTextSize = 10f
        }
    }

    val heightDataSet = remember {
        LineDataSet(heightEntries, "Height (cm)").apply {
            color = android.graphics.Color.RED
            setCircleColor(android.graphics.Color.RED)
            lineWidth = 2f
            circleRadius = 5f
            valueTextSize = 10f
        }
    }

    val lineData = remember {
        LineData(weightDataSet, heightDataSet)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Baby Growth Chart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Month input
        OutlinedTextField(
            value = month,
            onValueChange = { month = it },
            label = { Text("Month") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Weight input
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Height input
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                if (
                    month.isNotEmpty() &&
                    weight.isNotEmpty() &&
                    height.isNotEmpty()
                ) {

                    growthHistory.add(
                        GrowthRecord(
                            month = month,
                            weight = weight,
                            height = height
                        )
                    )

                    val monthValue = month.toFloat()
                    val weightValue = weight.toFloat()
                    val heightValue = height.toFloat()

                    weightEntries.add(
                        Entry(monthValue, weightValue)
                    )

                    heightEntries.add(
                        Entry(monthValue, heightValue)
                    )

                    weightDataSet.values = weightEntries
                    heightDataSet.values = heightEntries

                    lineData.notifyDataChanged()

                    month = ""
                    weight = ""
                    height = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Growth")
        }

        Spacer(modifier = Modifier.height(20.dp))

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),

            factory = { context ->

                LineChart(context).apply {

                    data = lineData

                    description.isEnabled = false
                    legend.isEnabled = true
                    setTouchEnabled(true)
                    setPinchZoom(true)

                    // LEFT AXIS (HEIGHT)
                    axisLeft.apply {
                        axisMinimum = 40f
                        axisMaximum = 70f
                    }

                    // RIGHT AXIS (WEIGHT)
                    axisRight.apply {
                        isEnabled = true
                        axisMinimum = 0f
                        axisMaximum = 10f
                    }

                    xAxis.granularity = 1f

                    invalidate()
                }
            },

            update = { chart ->

                chart.data = lineData
                chart.notifyDataSetChanged()
                chart.invalidate()
            }
        )
    }
}

        @Composable
        fun ShishuSnehNavigation() {

            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ) { paddingValues ->

                NavHost(
                    navController = navController,
                    startDestination = "login",
                    modifier = Modifier.padding(paddingValues)
                ) {

                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("home") {
                        ShishuSnehApp(navController)
                    }

                    composable("profile") {
                        ChildProfileScreen(navController)
                    }

                    composable("growth_tracker") {
                        GrowthChartScreen(navController)
                    }

                    composable("vaccination") {
                        VaccinationReminderScreen(navController)
                    }

                    composable(route = "feeding_guide") {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {

                            Button(
                                onClick = {
                                    navController.popBackStack()
                                }
                            ) {
                                Text("Back")
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Feeding Guide",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text("0–6 Months: Breast milk only")
                            Text("6–12 Months: Soft foods + milk")
                            Text("1–2 Years: Fruits, vegetables, rice")
                            Text("2+ Years: Healthy balanced diet")
                        }
                    }

                    composable("health_report") {
                        HealthReportScreen(navController)
                    }

                    composable("milestone") {
                        MilestoneScreen(navController)
                    }

                    composable("emergency") {
                        EmergencyScreen(navController)
                    }
                }
            }
        }

        @Composable
        fun BottomNavigationBar(navController: NavHostController) {

            NavigationBar {

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                    },
                    icon = { Text("🏠") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("growth_tracker")
                    },
                    icon = { Text("📈") },
                    label = { Text("Growth") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("vaccination")
                    },
                    icon = { Text("💉") },
                    label = { Text("Vaccine") }
                )
            }
        }

        @Composable
        fun SimpleScreen(
            title: String,
            navController: NavHostController
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Back")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = title,
                    fontSize = 24.sp
                )
            }
        }
@Composable
fun ChildProfileScreen(
    navController: NavHostController
) {
    val context = LocalContext.current

    val sharedPreferences =
        context.getSharedPreferences(
            "ChildProfile",
            Context.MODE_PRIVATE
        )

    var profileBabyName by remember {
        mutableStateOf(
            sharedPreferences.getString(
                "BabyName",
                ""
            ) ?: ""
        )
    }

    var babyAge by remember {
        mutableStateOf(
            sharedPreferences.getString(
                "babyAge",
                ""
            ) ?: ""
        )
    }

    var profilePhoneNumber by remember {
        mutableStateOf(
            sharedPreferences.getString(
                "phoneNumber",
                ""
            ) ?: ""
        )
    }

    var dob by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var parentName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Baby Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = profileBabyName,
            onValueChange = { profileBabyName = it },
            label = { Text("Baby Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dob,
            onValueChange = { dob = it },
            label = { Text("Date of Birth (DD/MM/YYYY)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bloodGroup,
            onValueChange = { bloodGroup = it },
            label = { Text("Blood Group") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = parentName,
            onValueChange = { parentName = it },
            label = { Text("Parent Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = profilePhoneNumber,
            onValueChange = { profilePhoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))


        Button(
            onClick = {

                sharedPreferences.edit()
                    .putString("babyName", profileBabyName)
                    .putString("babyDOB", dob)
                    .putString("babyAge", babyAge)
                    .putString("phoneNumber", profilePhoneNumber)
                    .apply()

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A4C93)
            )
        ) {
            Text("Save Profile")
        }
    }
}






