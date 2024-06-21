package com.example.educationapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.educationapp.components.BottomBar
import com.example.educationapp.components.HeaderCourse
import com.example.educationapp.components.SearchBarUi
import com.example.educationapp.ui.theme.BackField
import com.example.educationapp.ui.theme.EducationAppTheme
import com.example.educationapp.viewmodels.CourseViewModel
import com.example.educationapp.viewmodels.SignUpViewModel

class Course : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EducationAppTheme {
                Course()
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Course(navController: NavController){

    //var query by remember { mutableStateOf("") }
    val courseUpViewModel = viewModel<CourseViewModel>()

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally){

        HeaderCourse(text = "Course" , navController, title = "Log Out")

        SearchBarUi(query = courseUpViewModel.query.value,
            onQueryChange = {courseUpViewModel.queryUser(it)},
            )
        Spacer(modifier = Modifier.height(16.dp))


        Content(title = "Java", description = "Pellentesque eget urna sit amet lacus rutrum placerat ac vel mi.", image = R.drawable.javalogo ,navController = rememberNavController() )

        Spacer(modifier = Modifier.height(12.dp))

        Content(title = "Kotlin", description = "Pellentesque eget urna sit amet lacus rutrum placerat ac vel mi.", image = R.drawable.kotlinlogo,navController = rememberNavController())

        Spacer(modifier = Modifier.height(12.dp))

        Content(title = "Python", description = "Pellentesque eget urna sit amet lacus rutrum placerat ac vel mi.", image = R.drawable.pythonlogo,navController = rememberNavController())

        Spacer(modifier = Modifier.height(12.dp))

        Content(title = "Go", description = "Pellentesque eget urna sit amet lacus rutrum placerat ac vel mi.", image = R.drawable.gologo,navController = rememberNavController())

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier
            .height(227.98.dp)
            .width(343.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(BackField)
            .clickable { navController.navigate("classes") }
        ){
            Image(painterResource(R.drawable.onlineclass), contentDescription ="" )
        }
         NavApp(navController)
    }

}

@Preview
@Composable
fun CoursePreview(){
    val navController = rememberNavController()
    Course(navController)
}