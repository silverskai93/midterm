package android.example.midterm.util

import org.junit.Assert

fun <T> T.assertEqual(expected: T) {
    Assert.assertEquals(expected, this)
}

fun <T> T.assertNotEqual(unexpected: T) {
    Assert.assertNotEquals(unexpected, this)
}

fun Boolean?.assertFalse() {
    assertNotNull()
    this?.let { Assert.assertFalse(it) }
}

fun Boolean?.assertTrue() {
    assertNotNull()
    this?.let { Assert.assertTrue(it) }
}

inline fun <reified T> Any?.assertType(action: T.() -> Unit) {
    (this is T).assertTrue()
    action(this as T)
}

fun <T> T.assertNotNull() {
    Assert.assertNotNull(this)
}

fun <T> T?.assertNull() {
    Assert.assertNull(this)
}

fun List<*>?.assertListSize(actual: Int) {
    assertNotNull()
    this!!.count().assertEqual(actual)
}

fun Array<*>?.assertSize(actual: Int) {
    assertNotNull()
    this!!.count().assertEqual(actual)
}

fun List<*>?.assertListEmpty() {
    assertListSize(0)
}

fun Array<*>?.assertEmpty() {
    assertSize(0)
}
