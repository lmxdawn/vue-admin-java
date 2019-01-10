package com.lmxdawn.api.admin.form.ad;

import com.lmxdawn.api.admin.form.ListPageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdQueryForm extends ListPageForm {

    private String title;

}
