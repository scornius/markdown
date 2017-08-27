package org.nerdizin.markdown;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownConverter {

	public static void main(final String[] args) throws IOException, URISyntaxException  {
		
		final String content = new String(Files.readAllBytes(
				Paths.get(ClassLoader.getSystemResource("test.md").toURI())));
		
		//render(content);
		renderWithExtensions(content);
	}
	
	private static void render(final String content) throws IOException, URISyntaxException {
		
		final Parser parser = Parser.builder().build();
		final Node document = parser.parse(content);
		final HtmlRenderer renderer = HtmlRenderer.builder().build();
		System.out.println(renderer.render(document));
	}
	
	private static void renderWithExtensions(final String content) throws IOException, URISyntaxException {
		
		final List<Extension> extensions = Arrays.asList(
				HeadingAnchorExtension.create(),
				TablesExtension.create()
		);
		final Parser parser = Parser.builder()
		        .extensions(extensions)
		        .build();
		final Node document = parser.parse(content);
		final HtmlRenderer renderer = HtmlRenderer.builder()
		        .extensions(extensions)
		        .build();
		System.out.println(renderer.render(document));
	}
	
}
