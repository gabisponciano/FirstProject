package com.example.educationapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.educationapp.components.BottomBar
import com.example.educationapp.components.CustomBox
import com.example.educationapp.components.HeaderCourse
import com.example.educationapp.components.SearchBarUi
import com.example.educationapp.components.Slider
import com.example.educationapp.viewmodels.ClassViewModel
import com.example.educationapp.viewmodels.SignUpViewModel

@Composable
fun Classes(navController: NavController){
    val customBoxes = remember {
        mutableListOf(
            CustomBox("Python Aula 01", "Introdução ao Desenvolvimento"),
            CustomBox("Python Aula 02", "Objetos e Classes"),
            CustomBox("Python Aula 03", "Primeiro App")
        )}
    //var query by remember { mutableStateOf("") }

    val classViewModel = viewModel<ClassViewModel>()

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        HeaderCourse(text = "Aulas", navController, title = "Back")

        SearchBarUi(query = classViewModel.query.value,
            onQueryChange = {classViewModel.queryUser(it)}
            )
        Spacer(modifier = Modifier.height(16.dp))
        ConteudoAula(title = "Python", description = "dvfnjkdfnv skjfnkenfr vdkjfnk" )
        Spacer(modifier = Modifier.height(16.dp))
        Slider(customBoxes) {
        }
        NavApp(navController)


    }


}
@Preview
@Composable
fun ClassesPreview(){
    val navController = rememberNavController()
    Classes(navController)
}