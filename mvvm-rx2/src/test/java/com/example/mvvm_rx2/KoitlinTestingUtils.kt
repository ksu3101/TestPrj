package com.example.mvvm_rx2

import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing
import org.mockito.verification.VerificationMode

inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)

infix fun <T : Any> T.spy(obj: T): T = Mockito.spy(obj)

fun <T: Any> spy(obj: T): T = Mockito.spy(obj)

infix fun <T : Any> T.given(call: T?): OngoingStubbing<T> = Mockito.`when`(call)

fun <T: Any> given(call: T?): OngoingStubbing<T> = Mockito.`when`(call)

infix fun <T : Any> OngoingStubbing<T>.willReturn(returnValue: T?): OngoingStubbing<T> = this.thenReturn(returnValue)

infix fun <T : Any> OngoingStubbing<T>.willThrow(throwable: Throwable): OngoingStubbing<T> = this.thenThrow(throwable)

fun <T : Any> OngoingStubbing<T>.andAnswer(answer: Answer<Any>): OngoingStubbing<T> = this.thenAnswer(answer)

// then

infix fun <T : Any> T?.shouldBe(expected: T?) = this.apply { assertEquals(expected, this) }

infix fun <T : Any> T?.shouldNotBe(expected: T?) = this.apply { assertNotSame(expected, this) }

infix fun <T : Any> T?.shouldEqualTo(expected: T?) = this.apply { assertEquals(expected, this) }

infix fun <T : Any> T?.shouldNotEqualTo(expected: T?) = this.apply { assertNotSame(expected, this) }

// verify
fun <T : Any> T.verify(): T = Mockito.verify(this)
fun <T: Any> T.hasCalled(): T = this.verify()

fun <T : Any> T.verify(mode: VerificationMode): T = Mockito.verify(this, mode)

fun <T : Any> T.verifyTimes(stubCounts: Int): T = Mockito.verify(this, times(stubCounts))
fun <T: Any> T.hasCalledCounts(expectedInvocationCounts: Int): T = this.verifyTimes(expectedInvocationCounts)

fun <T : Any> T.verifyNever(): T = Mockito.verify(this, never())
fun <T: Any> T.hasNotCalled(): T = this.verifyNever()

fun <T : Any> T.verifyAtLeastOnce(): T = Mockito.verify(this, atLeastOnce())
fun <T: Any> T.hasCalledAtLeastOnce(): T = this.verifyAtLeastOnce()

fun <T : Any> T.verifyAtLeast(minNumberOfInvocations: Int): T = Mockito.verify(this, atLeast(minNumberOfInvocations))
fun <T : Any> T.hasCalledAtLeast(minNumberOfInvocations: Int): T = this.verifyAtLeast(minNumberOfInvocations)

fun <T : Any> T.verifyAtMost(maxNumberOfInvocations: Int): T = Mockito.verify(this, atMost(maxNumberOfInvocations))
fun <T: Any> T.hasCalledAtMost(maxNumberOfInvocations: Int): T = this.verifyAtMost(maxNumberOfInvocations)

fun <T : Any> T.verifyCalls(wantedNumberOfInvocations: Int): T = Mockito.verify(this, calls(wantedNumberOfInvocations))

// numbers (Numbers interface 로 바꿀순 없나?)

infix fun Int.biggerThan(expected: Int) = (this > expected)

infix fun Int.biggerThanOrEqualTo(expected: Int) = (this >= expected)

infix fun Int.smallThan(expected: Int) = (this < expected)

infix fun Int.smallThanOrEqualTo(expected: Int) = (this <= expected)

infix fun Float.biggerThan(expected: Float) = (this > expected)

infix fun Float.biggerThanOrEqualTo(expected: Float) = (this >= expected)

infix fun Float.smallThan(expected: Float) = (this < expected)

infix fun Float.smallThanOrEqualTo(expected: Float) = (this <= expected)

infix fun Long.biggerThan(expected: Long) = (this > expected)

infix fun Long.biggerThanOrEqualTo(expected: Long) = (this >= expected)

infix fun Long.smallThan(expected: Long) = (this < expected)

infix fun Long.smallThanOrEqualTo(expected: Long) = (this <= expected)

// Test Observable functions

infix fun <T : Any> TestObserver<T>.assertValueAtFirst(predicate: Predicate<T>) = this.apply { assertValueAt(0, predicate) }

infix fun <T : Any> TestObserver<T>.assertValueAtSecond(predicate: Predicate<T>) = this.apply { assertValueAt(1, predicate) }

infix fun <T : Any> TestObserver<T>.assertValueAtThird(predicate: Predicate<T>) = this.apply { assertValueAt(2, predicate) }

infix fun <T : Any> TestObserver<T>.assertValueAtFourth(predicate: Predicate<T>) = this.apply { assertValueAt(3, predicate) }

infix fun <T : Any> TestObserver<T>.assertValueAtFifth(predicate: Predicate<T>) = this.apply { assertValueAt(4, predicate) }

infix fun <T : Any> TestObserver<T>.assertError(predicate: Predicate<Throwable>) = this.apply { assertError(predicate) }

infix fun <T : Any> TestObserver<T>.assertValueCount(expectedCount: Int) = this.apply { assertValueCount(expectedCount) }

infix fun <T : Any> TestObserver<T>.assertCompleted(isCompleted: Boolean) = this.apply {
    if (isCompleted) assertComplete() else assertNotComplete()
}

fun <T : Any> TestObserver<T>.assertNoErrorOccurred() = this.apply { assertNoErrors() }

// Test Subscriber's functions

infix fun <T: Any> TestSubscriber<T>.assertValueAtFirst(predicate: Predicate<T>) = this. apply { assertValueAt(0, predicate) }

infix fun <T: Any> TestSubscriber<T>.assertValueAtSecond(predicate: Predicate<T>) = this.apply { assertValueAt(1, predicate) }

infix fun <T: Any> TestSubscriber<T>.assertValueAtThird(predicate: Predicate<T>) = this.apply { assertValueAt(2, predicate) }

infix fun <T: Any> TestSubscriber<T>.assertValueAtFourth(predicate: Predicate<T>) = this.apply { assertValueAt(3, predicate) }

infix fun <T: Any> TestSubscriber<T>.assertValueAtFifth(predicate: Predicate<T>) = this.apply { assertValueAt(4, predicate) }

infix fun <T: Any> TestSubscriber<T>.assertValueCount(expectedCount: Int) = this.apply { assertValueCount(expectedCount) }

infix fun <T: Any> TestSubscriber<T>.asserCompleted(isCompleted: Boolean) = this.apply {
    if (isCompleted) assertComplete() else assertNotComplete()
}

fun <T : Any> TestSubscriber<T>.assertNoErrorOccured() = this.apply { assertNoErrors() }