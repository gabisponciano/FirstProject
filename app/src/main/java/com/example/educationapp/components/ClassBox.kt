package com.example.educationapp.components

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.educationapp.ui.theme.BackField
import com.example.educationapp.ui.theme.Inter
import com.example.educationapp.ui.theme.Primary_Green
import com.example.educationapp.ui.theme.TextFieldText
import com.example.educationapp.viewmodels.ClassBoxModelView
import com.example.educationapp.viewmodels.SignUpViewModel

data class CustomBox(
    val title:String,
    val description:String,
    var isChecked:Boolean = false
)

@Composable
fun Slider(navController: NavController){
    val boxModelView = viewModel<ClassBoxModelView>()
    val customBoxes = boxModelView.customBoxes
   //Para conseguir deslizar
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Quando a caixa é marcada ou desmarcada, atualiza o estado na ViewModel
        items(customBoxes){ box -> // Cada item da lista é representada pela box
            BoxCheck(box){updatedBox, isChecked ->
                boxModelView.updateBoxCheck(updatedBox, isChecked)// Atualiza o estado da box
            }
        }
    }
    //Quando todas marcadas, aparece o botão de certificado
    if (boxModelView.allChecked.value){
        Button(onClick = { navController.navigate("certificate") },
            colors = ButtonDefaults.buttonColors(containerColor = Primary_Green),
            modifier = Modifier
                .height(65.dp)
                .width(343.dp)
                .padding(10.dp)

        ){
            Text(
                text = "Get Your Certificate",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                color = Color.White)
        }
    }
}
@Composable
fun BoxCheck(box: CustomBox, onCheckedChange:(CustomBox, Boolean)->Unit){
    var isChecked by remember { mutableStateOf(box.isChecked) }

    Box(modifier = Modifier
        .size(350.dp)
        .padding(5.dp)
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Row {
                Box(modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .height(40.dp),
                ){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Checkbox(
                            //Aqui quando marcada não desmarca mais
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                if (!isChecked) {
                                    isChecked = checked
                                    onCheckedChange(box, checked)
                                }
                                }

                        )

                        Text(text = box.description,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(150.dp))

                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(343.dp)
                .height(275.dp)
                .background(Primary_Green),
                contentAlignment = Alignment.Center
            ){

                Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

                    Text(text = box.title,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = box.description,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }


            }
        }
    }
}

@Preview
@Composable
fun SliderPreview(){
    val navController = rememberNavController()
    Slider(navController)
}