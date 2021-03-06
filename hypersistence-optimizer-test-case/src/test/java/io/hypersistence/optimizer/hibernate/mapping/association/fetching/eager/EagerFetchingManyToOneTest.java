/*
 * Copyright (c) 2019, Mihalcea Vlad-Alexandru (https://vladmihalcea.com)
 * All rights reserved.
 *
 * Mihalcea Vlad-Alexandru grants the Customer the non-exclusive,
 * timely limited and non-transferable license to install and use the Software
 * under the terms of the Hypersistence Optimizer License Agreement.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * See the Hypersistence Optimizer License Agreement for more details:
 *
 * https://vladmihalcea.com/hypersistence-optimizer/license
 */
package io.hypersistence.optimizer.hibernate.mapping.association.fetching.eager;

import io.hypersistence.optimizer.core.event.Event;
import io.hypersistence.optimizer.hibernate.event.mapping.association.fetching.EagerFetchingEvent;
import io.hypersistence.optimizer.util.AbstractHypersistenceOptimizerTest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Vlad Mihalcea
 */
public class EagerFetchingManyToOneTest extends AbstractHypersistenceOptimizerTest {

    @Override
    public Class<?>[] entities() {
        return new Class<?>[]{
                Post.class,
                PostComment.class
        };
    }

    @Override
    protected void verify() {
        List<Event> events = listEventListener().getEvents();
        assertEquals(1, events.size());
        assertTrue(events.get(0) instanceof EagerFetchingEvent);
    }

    @Entity(name = "Post")
    @Table(name = "post")
    public static class Post {

        @Id
        private Long id;

        private String title;
    }

    @Entity(name = "PostComment")
    @Table(name = "post_comment")
    public static class PostComment {

        @Id
        private Long id;

        @ManyToOne
        private Post post;

        private String review;

    }
}