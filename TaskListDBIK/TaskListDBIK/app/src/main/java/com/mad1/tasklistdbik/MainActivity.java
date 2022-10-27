package com.mad1.tasklistdbik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mad1.tasklistdbik.data.Task;
import com.mad1.tasklistdbik.database.TaskListDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // get db and StringBuilder objects
        TaskListDB db = new TaskListDB(this);
        StringBuilder sb = new StringBuilder();
// insert a task
        Task task = new Task(
                1, "Make dentist appointment", "", "0", "0");
        long insertId = db.insertTask(task);
        if (insertId > 0) {
            sb.append("Row inserted! Insert Id: " + insertId + "\n");
        }
// update a task
        task.setTaskId((int) insertId);
        task.setName("Update test");
        int updateCount = db.updateTask(task);
        if (updateCount == 1) {
            sb.append("Task updated!Update count: " + updateCount + "\n");
        }

        // delete a task
        int deleteCount = db.deleteTask(insertId);
        if (deleteCount == 1) {
            sb.append("Task deleted!Delete count: " + deleteCount + "\n\n");
        }
// display all tasks (id + name)
        ArrayList<Task> tasks = db.getTasks("Personal");
        for (Task t : tasks) {
            sb.append(t.getTaskId() + "|" + t.getName() + "\n");
        }
// display string on UI
        TextView taskListTextView = (TextView)
                findViewById (R.id.taskListTextView);
        taskListTextView.setText(sb.toString());
    }
}