package com.scin.sdk.bean.battery;

import com.scin.sdk.bean.share.BmsWarn;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *     电池BMS告警数据
 * </p>
 *
 * @author seven
 * @since 2020-05-20
 */
@Data
@Accessors(chain = true)
public class BmsWarnData extends BmsWarn {
}
