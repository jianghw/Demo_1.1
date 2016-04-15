package com.cjy.jianghw.app;

        import android.content.Context;
        import android.support.annotation.NonNull;

        import com.cjy.jianghw.app.data.DataSourceible;
        import com.cjy.jianghw.app.data.FakeTasksRemoteDataSource;
        import com.cjy.jianghw.app.data.ScrollingRepository;
        import com.cjy.jianghw.app.data.local.TasksLocalDataSource;

        import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for{@link DataSourceible} at compile time.
 * This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static ScrollingRepository provideTwoRepository(@NonNull Context context) {
        checkNotNull(context);

        return ScrollingRepository.getInstance(
                FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(context));
    }
}