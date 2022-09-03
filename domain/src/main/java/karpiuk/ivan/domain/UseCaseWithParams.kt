package karpiuk.ivan.domain

import kotlinx.coroutines.flow.Flow

abstract class UseCaseWithParams<in T, out R> {
    abstract operator fun invoke(params: T): Flow<R>
}