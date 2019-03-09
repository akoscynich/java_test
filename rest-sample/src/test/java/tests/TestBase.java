package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public int createIssue(Issue issue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json?limit=1000")
                .bodyForm(new BasicNameValuePair("subject", issue.getSubject()),
                        new BasicNameValuePair("description", issue.getDescription()))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?limit=1000"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        return status(issueId).equals("Closed");
    }

    private String status(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue = issues.getAsJsonArray().get(0);
        JsonElement status = issue.getAsJsonObject().get("status");
        return new Gson().fromJson(status, new TypeToken<String>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
