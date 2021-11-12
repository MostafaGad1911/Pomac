package mostafa.projects.pomactask.di.component

import dagger.Component
import mostafa.projects.pomactask.ui.activities.MainActivity
import mostafa.projects.pomactask.di.modules.PomacModule
import mostafa.projects.pomactask.viewModels.PomacViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [PomacModule::class])
interface PomacComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(pomacViewModel: PomacViewModel)
}