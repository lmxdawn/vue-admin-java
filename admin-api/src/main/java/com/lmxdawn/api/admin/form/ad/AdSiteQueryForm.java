package com.lmxdawn.api.admin.form.ad;

import com.lmxdawn.api.admin.form.ListPageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdSiteQueryForm extends ListPageForm {

    private Long siteId;

}
