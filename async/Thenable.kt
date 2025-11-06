package frc.beaverlib.async

@Deprecated("Thenable is deprecated, if you need to use it, idk why you would need to")
interface Thenable<T> {
    fun<N> then(success: (value: T) -> Promise<N>): Promise<N>
    fun<N> then(success: (value: T) -> Promise<N>, failure: (error: Throwable) -> Promise<N>) : Promise<N>
    fun catch(failure: (error: Throwable) -> Promise<T>): Promise<T>
}