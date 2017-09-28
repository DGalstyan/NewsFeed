package dg.com.news;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


/**
 * Created by dgalstya on 28.09.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView view;
    @Mock
    FindItemsInteractor interactor;

    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(view, interactor);
    }

    @Test
    public void checkIfHideFooterOnError () {
        presenter.onError();
        verify(view, times(1)).hideFooter();
    }

    @Test
    public void checkIfUpdateContainersOnFinish() {
        presenter.onFinished();
        verify(view, times(1)).updateContainers();
    }

    @Test
    public void checkIfChengedUpdateonSuccess() {
        ArgumentCaptor<Boolean> captor = forClass(Boolean.class);
        presenter.onSuccess();
        verify(view, times(1)).setUpdate(captor.capture());
        assertThat(captor.getValue(), is(true));
    }


    @Test
    public void checkIfUpdateFeeds() {
        presenter.updateFeeds();
        verify(view, times(1)).isOnline();
    }

    @Test
    public void checkIfChangedTotalItemsCount() {
        presenter.setTotalItemsCount(10);
        verify(view, times(1)).setMaxItems(10);
    }
}