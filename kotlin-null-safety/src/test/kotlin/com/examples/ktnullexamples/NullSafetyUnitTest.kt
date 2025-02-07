package com.examples.ktnullexamples

import com.examples.ktnullexamples.nullsafety.NullSafety
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NullSafetyUnitTest {

    private val nullSafetyTestClass: NullSafety = NullSafety()

    @Test
    fun givenNonNullableString_whenNullCheckIsDone_thenReturnsLength() {
        assertEquals(8, nullSafetyTestClass.nullCheckForValue())
    }

    @Test
    fun givenNullString_whenNullCheckIsDone_thenReturnsNull() {
        assertNull(nullSafetyTestClass.nullCheck())
    }

    @Test
    fun givenNonNullableString_whenSafeCallIsDone_thenReturnsLength() {
        assertEquals(13, nullSafetyTestClass.safeCallForValue())
    }

    @Test
    fun givenNullString_whenSafeCallIsDone_thenReturnsNull() {
        assertNull(nullSafetyTestClass.safeCall())
    }

    @Test
    fun givenNonNullableString_whenSafeCallChainIsDone_thenReturnsCityCode() {
        assertEquals("007", nullSafetyTestClass.safeCallChainForValue())
    }

    @Test
    fun givenNullableString_whenSafeCallChainIsDone_thenReturnsNull() {
        assertEquals("007", nullSafetyTestClass.safeCallChainForValue())
        assertNull(nullSafetyTestClass.safeCallChain())
    }

    @Test
    fun givenList_whenSafeCallWithLet_thenReturnsOnlyNonNullableElements() {
        assertEquals(2, nullSafetyTestClass.safeCallWithLet().size)
        assertEquals("Bismark", nullSafetyTestClass.safeCallWithLet()[0])
    }

    @Test
    fun givenList_whenSafeCallWithLetAndAlso_thenReturnsOnlyNonNullableElements() {
        assertEquals(2, nullSafetyTestClass.safeCallWithAlso().size)
    }

    @Test
    fun givenList_whenSafeCallWitRun_thenReturnsOnlyNonNullableElements() {
        assertEquals(2, nullSafetyTestClass.safeCallWithRun().size)
        assertEquals("Germany", nullSafetyTestClass.safeCallWithRun()[1])
    }

    @Test
    fun givenNullableString_whenElvisOperatorIsUsed_thenReturnsDefaultValue() {
        assertEquals("Not available", nullSafetyTestClass.elvisOperatorForDefaultValue())
    }

    @Test
    fun givenNonNullableString_whenElvisOperatorIsUsed_thenReturnsValue() {
        assertEquals("006", nullSafetyTestClass.elvisOperator())
    }

    @Test
    fun givenNullableString_whenElvisOperatorIsUsed_thenThrowsException() {
        assertThrows<IllegalArgumentException> { nullSafetyTestClass.elvisOperatorForException() }
    }

    @Test
    fun givenNonNullableString_whenNotNullAssertionOperatorIsUsed_thenReturnsValue() {
        assertEquals(5, nullSafetyTestClass.notNullAssertion())
    }

    @Test
    fun givenNullableString_whenNotNullAssertionOperatorIsUsed_thenThrowsException() {
        assertThrows<NullPointerException> { nullSafetyTestClass.notNullAssertionForException() }
    }

    @Test
    fun givenList_whenFilterNotNullIsUsed_thenReturnsOnlyNonNullableElements() {
        assertEquals(3, nullSafetyTestClass.filterNotNullList().size)
        assertEquals("Russia", nullSafetyTestClass.filterNotNullList()[2])
    }
}