package wonjjong.dev.ottservice.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

@Configuration
public class P6SpySqlFormatter implements MessageFormattingStrategy {

	// 표기에 허용되지 않는 filter
	private List<String> DENIED_FILTER = Arrays.asList("Test1"
			, this.getClass().getSimpleName());
	// 표기에 허용되는 filter
	private String ALLOW_FILTER = "wonjjong.dev";

	@PostConstruct
	public void setLogMessageFormat() {
		P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
	}

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
		sql = formatSql(category, sql);
		return String.format("[%s] | %d ms | %s", category, elapsed, formatSql(category, sql)) + createStack(connectionId,elapsed);
	}

	private String formatSql(String category, String sql) {
		if (sql != null && !sql.trim().isEmpty() && Category.STATEMENT.getName().equals(category)) {
			String trimmedSQL = sql.trim().toLowerCase(Locale.ROOT);
			if (trimmedSQL.startsWith("create") || trimmedSQL.startsWith("alter") || trimmedSQL.startsWith("comment")) {
				sql = FormatStyle.DDL.getFormatter().format(sql);
			} else {
				sql = FormatStyle.BASIC.getFormatter().format(sql);
			}
			return sql;
		}
		return sql;
	}

	private String createStack(int connectionId, long elapsed) {
		Stack<String> callStack = new Stack<>();
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();

		for (StackTraceElement stackTraceElement : stackTrace) {
			String trace = stackTraceElement.toString();

			// trace 항목을 보고 내게 맞는 것만 필터
//			if(trace.startsWith(ALLOW_FILTER) && !filterDenied(trace)) {
			if(trace.startsWith(ALLOW_FILTER)) {
				callStack.push(trace);
			}
		}

		StringBuffer sb = new StringBuffer();
		int order = 1;
		while (callStack.size() != 0) {
			sb.append("\n\t\t" + (order++) + "." + callStack.pop());
		}

		return new StringBuffer().append("\n\n\tConnection ID:").append(connectionId)
				.append(" | Excution Time:").append(elapsed).append(" ms\n")
				.append("\n\tExcution Time:").append(elapsed).append(" ms\n")
				.append("\n\tCall Stack :").append(sb).append("\n")
				.append("\n--------------------------------------")
				.toString();
	}


}
