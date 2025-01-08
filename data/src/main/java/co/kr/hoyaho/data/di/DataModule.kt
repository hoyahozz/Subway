package co.kr.hoyaho.data.di

import co.kr.hoyaho.data.repository.SubwayRepositoryImpl
import co.kr.hoyaho.domain.repository.SubwayRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindsSubwayRepository(
        repository: SubwayRepositoryImpl,
    ): SubwayRepository
}
