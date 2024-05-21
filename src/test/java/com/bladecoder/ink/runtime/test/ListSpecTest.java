package com.bladecoder.ink.runtime.test;

import com.bladecoder.ink.runtime.Story;
import org.junit.Assert;
import org.junit.Test;

public class ListSpecTest {

    /**
     * "- testListBasicOperations"
     */
    @Test
    public void testListBasicOperations() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/basic-operations.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("b, d\na, b, c, e\nb, c\nfalse\ntrue\ntrue\n", story.continueMaximally());
    }

    /**
     * "- TestListMixedItems"
     */
    @Test
    public void testListMixedItems() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/list-mixed-items.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("a, y, c\n", story.continueMaximally());
    }

    /**
     * "- TestMoreListOperations"
     */
    @Test
    public void testMoreListOperations() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/more-list-operations.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("1\nl\nn\nl, m\nn\n", story.continueMaximally());
    }

    /**
     * "- TestEmptyListOrigin"
     */
    @Test
    public void testEmptyListOrigin() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/empty-list-origin.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("a, b\n", story.continueMaximally());
    }

    /**
     * "- TestListSaveLoad"
     */
    @Test
    public void testListSaveLoad() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/list-save-load.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("a, x, c\n", story.continueMaximally());

        String savedState = story.getState().toJson();

        // Compile new version of the story
        story = new Story(json);

        // Load saved game
        story.getState().loadJson(savedState);

        story.choosePathString("elsewhere");
        // FIXME: This is the test from the C# impl. Is it correct?
        //		Assert.assertEquals("a, x, c, z\n", story.continueMaximally());

        Assert.assertEquals("z\n", story.continueMaximally());
    }

    /**
     * "- TestEmptyListOriginAfterAssignment"
     */
    @Test
    public void testEmptyListOriginAfterAssignment() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/empty-list-origin-after-assignment.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("a, b, c\n", story.continueMaximally());
    }

    @Test
    public void testListRange() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/list-range.ink.json");
        Story story = new Story(json);

        Assert.assertEquals(
                "Pound, Pizza, Euro, Pasta, Dollar, Curry, Paella\nEuro, Pasta, Dollar, Curry\nTwo, Three, Four, Five, Six\nPizza, Pasta\n",
                story.continueMaximally());
    }

    @Test
    public void testBugAddingElement() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/bug-adding-element.ink.json");
        Story story = new Story(json);

        String s = story.continueMaximally();
        Assert.assertEquals("", s);

        story.chooseChoiceIndex(0);
        s = story.continueMaximally();
        Assert.assertEquals("a\n", s);

        story.chooseChoiceIndex(1);
        s = story.continueMaximally();
        Assert.assertEquals("OK\n", s);
    }

    @Test
    public void testMoreListOperations2() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/more-list-operations2.ink.json");
        Story story = new Story(json);

        Assert.assertEquals(
                "a1, b1, c1\na1\na1, b2\ncount:2\nmax:c2\nmin:a1\ntrue\ntrue\nfalse\nempty\na2\na2, b2, c2\nrange:a1, b2\na1\nsubtract:a1, c1\nrandom:a1\nlistinc:b1\n",
                story.continueMaximally());
    }

    @Test
    public void testListAllBug() throws Exception {

        String json = TestUtils.getJsonString("inkfiles/lists/list-all.ink.json");
        Story story = new Story(json);

        Assert.assertEquals("A, B\n", story.continueMaximally());
    }
}
