package org.highway.whatsup.data.test;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import org.highway.whatsup.data.uuid.HashedUuidGenerator;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by engeng on 8/25/15.
 */
public class HashedUuidGeneratorTest {

    @Test
    public void shouldReturnFakeUuidWhenContextReturnsNullApplicationInfo() {
        Context mockContext = mock(Context.class);
        when(mockContext.getApplicationInfo()).thenReturn(null);
        assertThat(HashedUuidGenerator.gen(mockContext), is("fakeUuid"));
    }
}
