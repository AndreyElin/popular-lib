package andrey.elin.githubclient.di.modules

import andrey.elin.githubclient.mvp.model.image.IImageLoader
import andrey.elin.githubclient.ui.image.GlideImageLoader
import android.widget.ImageView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader() : IImageLoader<ImageView> = GlideImageLoader()
}