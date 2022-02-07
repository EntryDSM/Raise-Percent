package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDocumentRequest {

	public List<PageRequest> pages;

	@Getter
	@NoArgsConstructor
	public static class PageRequest{
		public String content;
		public Integer page;
	}
}
