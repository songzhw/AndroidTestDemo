package ca.six.todo.tasks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.six.todo.data.Task;
import ca.six.todo.data.source.TasksDataSource;
import ca.six.todo.data.source.TasksRepository;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TasksPresenterTest {
    @Mock TasksContract.View view;
    @Mock TasksRepository repo;
    @Captor ArgumentCaptor<TasksDataSource.LoadTasksCallback> captor;
    private List<Task> tasks;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(view.isActive()).thenReturn(true);

        Task[] tmp = new Task[]{
                new Task("01","u", false),
                new Task("02","", false),
                new Task("03","", true),
                new Task("04","x", false)
        };
        tasks = Arrays.asList(tmp);
    }

    @Test
    public void testFirstEnters_listShouldBeEmpty(){
        TasksPresenter presenter = new TasksPresenter(repo, view);
        presenter.start();

        verify(view).setLoadingIndicator(true);
        verify(repo).refreshTasks();

        verify(repo).getTasks(captor.capture());
        captor.getValue().onTasksLoaded(new ArrayList<Task>());

        // default filter is : All_Tasks
        verify(view).showNoTasks();
    }

    @Test
    public void testFirstEnters_getDataFailed(){
        TasksPresenter presenter = new TasksPresenter(repo, view);
        presenter.start();

        verify(repo).getTasks(captor.capture());
        captor.getValue().onDataNotAvailable();

        verify(view).showLoadingTasksError();
    }
    @Test
    public void showMultipleTasks(){
        TasksPresenter presenter = new TasksPresenter(repo, view);
        presenter.start();

        verify(repo).getTasks(captor.capture());
        captor.getValue().onTasksLoaded(tasks);

        verify(view).showTasks(tasks);
        verify(view).showAllFilterLabel();

    }


    @Test
    public void testAddNewTask() {
        TasksPresenter presenter = new TasksPresenter(repo, view);
        presenter.addNewTask();

        verify(view).showAddTask();
    }


    @Test
    public void changeFilterToActive_showThreeTasks() {
        // fragment will call p.setFilter() and p.loadTasks(false);
        TasksPresenter presenter = new TasksPresenter(repo, view);
        presenter.setFiltering(TasksFilterType.ACTIVE_TASKS);
        presenter.loadTasks(false);

        List<Task> tasksToShow = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isActive()) {
                tasksToShow.add(task);
            }
        }
        assertEquals(3, tasksToShow.size());

        verify(repo).getTasks(captor.capture());
        captor.getValue().onTasksLoaded(tasks);

        verify(view).showTasks(tasksToShow);
        verify(view).showActiveFilterLabel();
    }



}
