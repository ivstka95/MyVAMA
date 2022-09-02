package karpiuk.ivan.domain

import kotlinx.coroutines.flow.Flow
import karpiuk.ivan.utils.Result

abstract class UseCaseWithParams<in T, out R> {
    abstract operator fun invoke(params: T): Flow<Result<R>>
}