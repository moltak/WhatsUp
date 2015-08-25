package org.highway.whatsup.data.test;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import org.highway.whatsup.data.mobileinfo.UuidGenerator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by engeng on 8/25/15.
 */
public class UuidGeneratorTest {

    @Test
    public void shouldReturnNullStringWhenContextReturnsApplicationInfo() {
        Context mockContext = mock(Context.class);
        when(mockContext.getApplicationInfo()).thenReturn(mock(ApplicationInfo.class));

        assertThat(UuidGenerator.gen(mockContext), is(""));
    }

    @Test
    public void shouldReturnFakeUuidWhenContextReturnsNullApplicationInfo() {
        Context mockContext = mock(Context.class);
        when(mockContext.getApplicationInfo()).thenReturn(null);
        assertThat(UuidGenerator.gen(mockContext), is("fakeUuid"));
    }
}
