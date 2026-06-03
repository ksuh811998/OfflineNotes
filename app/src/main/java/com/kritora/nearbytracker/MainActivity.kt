package com.kritora.nearbytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.*
import com.kritora.nearbytracker.data.worker.SyncWorker
import com.kritora.nearbytracker.presentation.add_task.AddTaskScreen
import com.kritora.nearbytracker.presentation.task_detail.TaskDetailScreen
import com.kritora.nearbytracker.presentation.task_list.TaskListScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setupSyncWorker()
        
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NearbyTrackerNavigation()
                }
            }
        }
    }

    private fun setupSyncWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "SyncTasksWork",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}

@Composable
fun NearbyTrackerNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "task_list"
    ) {
        composable("task_list") {
            TaskListScreen(
                onNavigateToDetail = { taskId ->
                    navController.navigate("task_detail/$taskId")
                },
                onNavigateToAddTask = {
                    navController.navigate("add_task")
                }
            )
        }
        composable(
            route = "task_detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) {
            TaskDetailScreen(
                onPopBackStack = { navController.popBackStack() }
            )
        }
        composable("add_task") {
            AddTaskScreen(
                onPopBackStack = { navController.popBackStack() }
            )
        }
    }
}
